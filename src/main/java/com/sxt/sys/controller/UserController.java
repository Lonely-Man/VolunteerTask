package com.sxt.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.UserVo;
import com.sxt.sys.common.AppFileUtils;
import com.sxt.sys.common.Constast;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.PinyinUtils;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.common.WebUtils;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.service.UserService;

import cn.hutool.core.util.IdUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private RoleService roleService;

	/**
	 * 用户全查询
	 */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo userVo) {
		IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(StringUtils.isNotBlank(userVo.getName()), "loginname", userVo.getName()).or()
				.eq(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
		queryWrapper.eq(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
		queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);// 查询系统用户
		queryWrapper.eq(userVo.getDeptid() != null, "deptid", userVo.getDeptid());
		this.userService.page(page, queryWrapper);

		System.out.println(userService.getClass().getSimpleName());
		List<User> list = page.getRecords();
		for (User user : list) {
			Integer deptid = user.getDeptid();
			if (deptid != null) {
				Dept dept = deptService.getById(deptid);
				user.setDeptname(dept.getTitle());
			}
			Integer mgr = user.getMgr();
			if (mgr != null) {
				User leader = this.userService.getById(mgr);
				user.setLeadername(leader.getName());
			}
		}

		return new DataGridView(page.getTotal(), list);

	}

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("loadUserByUId")
	public User loadUserByUId(Integer id) {

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", id);
		User user = this.userService.getOne(queryWrapper);

		if (user.getDeptid() != null) {
			// 根据部门ID拿到部门表的部门名字
			Dept dept = this.deptService.getById(user.getDeptid());
			user.setDeptname(dept.getTitle());
		}
		if (user.getMgr() != null) {
			// 根据领导ID拿到领导名字
			User leader = this.userService.getById(user.getMgr());
			user.setLeadername(leader.getName());
		}

		return user;
	}

	/**
	 * 加载最大的排序码
	 * @param
	 * @return
	 */
	@RequestMapping("loadUserMaxOrderNum")
	public Map<String, Object> loadDeptMaxOrderNum() {
		Map<String, Object> map = new HashMap<String, Object>();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("ordernum");
		List<User> list = this.userService.list(queryWrapper);
		if (list.size() > 0) {
			map.put("value", list.get(0).getOrdernum() + 1);
		} else {
			map.put("value", 1);
		}
		return map;
	}

	/**
	 * 根据部门ID查询用户
	 */
	@RequestMapping("loadUsersByDeptId")
	public DataGridView loadUsersByDeptId(Integer deptid) {
		QueryWrapper<User> quewrapper = new QueryWrapper<>();
		quewrapper.eq(deptid != null, "deptid", deptid);
		quewrapper.eq("available", Constast.AVAILABLE_TRUE);
		quewrapper.eq("type", Constast.USER_TYPE_NORMAL);
		List<User> list = this.userService.list(quewrapper);
		return new DataGridView(list);
	}

	/**
	 * 名字改拼音
	 */
	@RequestMapping("changeChineseToPinyin")
	public Map<String, Object> changeChineseToPinyin(String username) {
		Map<String, Object> map = new HashMap<>();
		if (username != null) {
			map.put("value", PinyinUtils.getPingYin(username));
		} else {
			map.put("value", "");
		}
		return map;
	}

	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo userVo) {
		try {
			userVo.setType(Constast.USER_TYPE_NORMAL);// 设置类型
			userVo.setHiredate(new Date());
			String salt = IdUtil.simpleUUID().toUpperCase();
			userVo.setSalt(salt);// 设置盐
			userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());// 设置密码
			userVo.setImgpath(Constast.IMAGES_DEFAULTIMG_JPG);
			this.userService.save(userVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 根据deptid查询用户
	 */
	@RequestMapping("loadUserById")
	public DataGridView loadUserById(Integer id) {
		return new DataGridView(this.userService.getById(id));
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo) {
		try {
			// 判断是否是默认图片
			if (userVo.getImgpath() != null && !userVo.getImgpath().equals(Constast.IMAGES_DEFAULTIMG_JPG)) {
				// 判断是否有_temp后缀
				if (userVo.getImgpath().endsWith("_temp")) {
					String newImgName = AppFileUtils.renameFile(userVo.getImgpath());
					userVo.setImgpath(newImgName);
					// 删除原先的图片
					String oldPath = this.userService.getById(userVo.getId()).getImgpath();
					if (!userVo.getImgpath().equals(Constast.IMAGES_DEFAULTIMG_JPG)) {
						AppFileUtils.removeFilePath(oldPath);
					}
				}
			}

			this.userService.updateById(userVo);
			User sessionUser = (User) WebUtils.getSession().getAttribute("user");
			// 判断当前sessionid和修改信息是否同一个人
			if (userVo.getId() == sessionUser.getId()) {
				// 把最新的用户信息更新到session
				QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
				queryWrapper.eq("id", userVo.getId());
				User newUser = userService.getOne(queryWrapper);
				WebUtils.getSession().setAttribute("user", newUser);
			}

			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 根据用户ID查询当前用户是否是其他用户的直属领导
	 * @param id
	 * @return
	 */
	@RequestMapping("queryMgrByUserId")
	public ResultObj queryMgrByUserId(Integer id) {
		Boolean isMgr = userService.queryMgrByUserId(id);
		if (isMgr) {
			return ResultObj.DELETE_ERROR_MGR;
		} else {
			return ResultObj.DELETE_SUCCESS;
		}
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(Integer id) {
		try {
			this.userService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 重置密码
	 */
	@RequestMapping("resetPwd")
	public ResultObj resetPwd(Integer id) {
		try {
			User user = new User();
			user.setId(id);
			String salt = IdUtil.simpleUUID().toUpperCase();
			user.setSalt(salt);// 设置盐
			user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());// 设置密码
			this.userService.updateById(user);
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}

	/**
	 * 根据用户id查询角色
	 * @param id
	 * @return
	 */
	@RequestMapping("initRoleByUserId")
	public DataGridView initRoleByUserId(Integer id) {
		// 查询可用的角色
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Map<String, Object>> maps = this.roleService.listMaps(queryWrapper);
		// 查询当前用户拥有的角色ID集合
		List<Integer> currentUserRoleIds = this.roleService.queryUserRoleIdsByUid(id);
		for (Map<String, Object> map : maps) {
			Boolean LAY_CHECKED = false;
			Integer roleid = (Integer) map.get("id");
			for (Integer rid : currentUserRoleIds) {
				if (rid == roleid) {
					LAY_CHECKED = true;
				}
			}
			map.put("LAY_CHECKED", LAY_CHECKED);
		}

		return new DataGridView(Long.valueOf(maps.size()), maps);
	}

	/**
	 * 保存用户和角色之间的关系
	 * @param uid
	 * @param ids
	 * @return
	 */
	@RequestMapping("saveUserRole")
	public ResultObj saveUserRole(Integer uid, Integer[] ids) {
		try {
			this.userService.saveUserRole(uid, ids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}

	/*
	 * 查询密码
	 */
	@RequestMapping("queryPwd")
	public ResultObj queryPwd(UserVo userVo) {
		// 1.拿到session的user属性
		User user = (User) WebUtils.getSession().getAttribute("user");
		// 2.拿到传过来的值之后加密加(user的盐)盐散列两次 如果一致则密码正确
		Object simpleHash = new SimpleHash("MD5", userVo.getPwd(), user.getSalt(), 2);
		if ((simpleHash.toString()).equals(user.getPwd())) {
			return ResultObj.PWD_SUCCESS;
		} else {
			return ResultObj.PWD_ERROR;
		}
	}

	/*
	 * 更改密码
	 */
	@RequestMapping("updatePwd")
	public ResultObj updatePwd(UserVo userVo) {
		try {
			User user = (User) WebUtils.getSession().getAttribute("user");
			// 生成密文
			Object simpleHash = new SimpleHash("MD5", userVo.getPwd(), user.getSalt(), 2);
			userVo.setId(user.getId());
			userVo.setPwd(simpleHash.toString());
			// 修改密码
			this.userService.updateById(userVo);

			// 把最新的用户信息更新到session
			QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
			queryWrapper.eq("id", user.getId());
			User newUser = userService.getOne(queryWrapper);
			WebUtils.getSession().setAttribute("user", newUser);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}
}
