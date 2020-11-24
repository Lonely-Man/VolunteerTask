package com.sxt.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.mapper.PermissionMapper;
import com.sxt.sys.service.PermissionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	@Override
	public boolean removeById(Serializable id) {
		PermissionMapper permissionMapper = this.getBaseMapper();
		// 根据权限或菜单ID删除权限表各和角色的关系表里面的数据
		permissionMapper.deleteRolePermissionByPid(id);
		return super.removeById(id);// 删除 权限表的数据
	}
}
