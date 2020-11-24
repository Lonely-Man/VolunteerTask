package com.sxt.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.mapper.DeptMapper;
import com.sxt.sys.service.DeptService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
	@Override
	public Dept getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean updateById(Dept entity) {
		// TODO Auto-generated method stub
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public boolean save(Dept entity) {
		return super.save(entity);
	}
}
