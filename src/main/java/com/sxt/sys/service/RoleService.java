package com.sxt.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.sys.domain.Role;

/**
 * <p>
 *  服务类
 * </p>
 *

 */
public interface RoleService extends IService<Role> {
	// 根据角色ID查询当前角色拥有的所有菜单和权限
	List<Integer> queryRolePermissionIdsByRid(Integer roleId);

	// 保存角色和菜单权限之间的关系
	void saveRolePermission(Integer roleId, Integer[] ids);

	List<Integer> queryUserRoleIdsByUid(Integer id);
}
