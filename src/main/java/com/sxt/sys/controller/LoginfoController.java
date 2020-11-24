package com.sxt.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.LoginfoVo;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.domain.Loginfo;
import com.sxt.sys.service.LoginfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {
	@Autowired
	private LoginfoService loginfoService;

	/**
	 * 全查询
	 */
	@RequestMapping("loadAllLoginfo")
	public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {

		IPage<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
		QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
		queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
		queryWrapper.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
		queryWrapper.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
		queryWrapper.orderByDesc("logintime");
		this.loginfoService.page(page, queryWrapper);

		return new DataGridView(page.getTotal(), page.getRecords());

	}

	/**
	 * 删除日志
	 */
	@RequestMapping("deleteLoginfo")
	public ResultObj deleteLoginfo(Integer id) {
		try {
			this.loginfoService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

	/**
	 * 批量删除日志
	 */
	@RequestMapping("batchDeleteLoginfo")
	public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo) {
		try {
			Collection<Serializable> idList = new ArrayList<>();
			for (Integer id : loginfoVo.getIds()) {
				idList.add(id);
			}
			this.loginfoService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

}
