package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.RoleVo;
import com.sxt.sys.common.Constast;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.common.TreeNode;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.domain.Role;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.service.RoleService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("loadAllRole")
	public DataGridView loadAllRole(RoleVo roleVo) {
		// 查询条件
		IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
		QueryWrapper<Role> queryWapper = new QueryWrapper<>();
		queryWapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
		queryWapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
		queryWapper.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
		this.roleService.page(page, queryWapper);
		return new DataGridView(page.getTotal(), page.getRecords());

	}

	/**
	 * 添加角色
	 */
	@RequestMapping("addRole")
	public ResultObj addRole(RoleVo roleVo) {
		try {
			roleVo.setCreatetime(new Date());
			this.roleService.save(roleVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}

	/**
	 * 添加角色
	 */
	@RequestMapping("updateRole")
	public ResultObj updateRole(RoleVo roleVo) {
		try {
			this.roleService.updateById(roleVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}

	}

	/**
	 * 删除角色
	 */
	@RequestMapping("deleteRole")
	public ResultObj deleteRole(Integer id) {
		try {
			this.roleService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

	/**
	 * 根据角色ID加载权限和菜单的树的JSON串
	 */
	@RequestMapping("initPermissionByRoleId")
	public DataGridView initPermissionByRoleId(Integer roleId) {
		// 查询所有可用的菜单和权限
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Permission> allPermission = this.permissionService.list(queryWrapper);
		/**
		 * 1.根据角色ID查询当前角色拥有的所有权限和菜单ID
		 * 2.根据查询出来的菜单ID查询权限和菜单数据
		 */
		List<Integer> currentRolePermissions = this.roleService.queryRolePermissionIdsByRid(roleId);
		List<Permission> currentPermissions = null;
		if (currentRolePermissions.size() > 0) {// 如果有Id就去查 没有就给个空集合
			queryWrapper.in("id", currentRolePermissions);
			currentPermissions = permissionService.list(queryWrapper);
		} else {
			currentPermissions = new ArrayList<>();
		}

		List<TreeNode> nodes = new ArrayList<>();
		for (Permission p1 : allPermission) {
			String checkArr = "0";
			for (Permission p2 : currentPermissions) {
				if (p1.getId() == p2.getId()) {
					checkArr = "1";
				}
			}
			Boolean spread = (p1.getOpen() == null || p1.getOpen() == 1) ? true : false;
			nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getTitle(), spread, checkArr));
		}
		return new DataGridView(nodes);
	}

	// 保存角色和菜单权限之间的关系
	@RequestMapping("saveRolePermission")
	public ResultObj saveRolePermission(Integer rid, Integer[] ids) {
		try {
			this.roleService.saveRolePermission(rid, ids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DISPATCH_ERROR;
		}

	}

}
