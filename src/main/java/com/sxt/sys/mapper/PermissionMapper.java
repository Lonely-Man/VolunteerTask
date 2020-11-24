package com.sxt.sys.mapper;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.sys.domain.Permission;

/**
 * <p>
 *  Mapper 接口
 * </p>

 */
public interface PermissionMapper extends BaseMapper<Permission> {
	void deleteRolePermissionByPid(Serializable id);
}
