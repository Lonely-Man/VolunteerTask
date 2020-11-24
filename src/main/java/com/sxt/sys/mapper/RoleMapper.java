package com.sxt.sys.mapper;

import com.sxt.sys.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 *
 */
public interface RoleMapper extends BaseMapper<Role> {

    void deleteRolePermissionById(Serializable id);

    void deleteRoleUserByRId(Serializable id);

    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    void saveRolePermission(@Param("rid") Integer rid, @Param("pid") Integer pid);

    void deleteRoleUserByUId(Serializable id);

    List<Integer> queryUserRoleIdsByUid(Integer id);

    void insertUserRole(@Param("uid")Integer uid, @Param("rid")Integer rid);
}
