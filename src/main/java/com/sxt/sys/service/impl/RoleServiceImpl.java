package com.sxt.sys.service.impl;

import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public boolean removeById(Serializable id) {
        //根据角色ID删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionById(id);
        //根据角色ID删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRId(id);

        return super.removeById(id);
    }

    //根据角色ID查询当前角色拥有的所有菜单和权限
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    //保存角色和菜单权限之间的关系
    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {

        RoleMapper roleMapper = this.getBaseMapper();
        //根据rid删除sys_role_permission
        roleMapper.deleteRolePermissionById(rid);
        if (ids!=null&&ids.length>0){
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid,pid);
            }
        }

    }

    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return this.getBaseMapper() .queryUserRoleIdsByUid(id);
    }
}
