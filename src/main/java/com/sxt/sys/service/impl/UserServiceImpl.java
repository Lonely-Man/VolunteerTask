package com.sxt.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean save(User entity) {
		return super.save(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		// 根据用户ID删除用户角色sys_role_user中间表的数据
		this.roleMapper.deleteRoleUserByUId(id);
		return super.removeById(id);
	}

	@Override
	public User getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean updateById(User entity) {
		return super.updateById(entity);
	}

	@Override
	public void saveUserRole(Integer uid, Integer[] ids) {
		// 根据用户id删除sys_user_role的数据
		this.roleMapper.deleteRoleUserByUId(uid);
		if (null != ids && ids.length > 0) {
			for (Integer rid : ids) {
				this.roleMapper.insertUserRole(uid, rid);
			}
		}
	}

	@Override
	public Boolean queryMgrByUserId(Integer userId) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.select("mgr");
		queryWrapper.eq("mgr",userId);
		List<User> users = userMapper.selectList(queryWrapper);
		if (null!=users&&users.size()>0){
			return true;
		}else {
			return false;
		}
	}
}
