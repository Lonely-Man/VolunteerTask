package com.sxt.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.LeavebillVo;
import com.sxt.sys.common.Constast;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.domain.Leavebill;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.LeavebillService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/leavebill")
public class LeavebillController {
	@Autowired
	private LeavebillService leavebillService;

	/**
	 * 加载所有请假单
	 * @param leavebillVo
	 * @return
	 */
	@RequestMapping("loadAllLeavebill")
	public DataGridView loadAllLeavebill(LeavebillVo leavebillVo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		QueryWrapper<Leavebill> queryWapper = null;
		IPage<Leavebill> page = new Page<>(leavebillVo.getPage(), leavebillVo.getLimit());
		if (user.getType() == Constast.USER_TYPE_SUPER) {
			// 查询条件
			queryWapper = new QueryWrapper<>();
			queryWapper.like(StringUtils.isNotBlank(leavebillVo.getTitle()), "title", leavebillVo.getTitle());
			queryWapper.like(StringUtils.isNotBlank(leavebillVo.getContent()), "content", leavebillVo.getContent());
			// ge大于等于 lte、le 小于等于
			queryWapper.ge(leavebillVo.getStartTime() != null, "leavetime", leavebillVo.getStartTime());
			queryWapper.le(leavebillVo.getEndTime() != null, "leavetime", leavebillVo.getEndTime());

		} else {
			queryWapper = new QueryWrapper<>();
			queryWapper.like(user.getId() != null, "userid", user.getId());
			queryWapper.like(StringUtils.isNotBlank(leavebillVo.getTitle()), "title", leavebillVo.getTitle());
			queryWapper.like(StringUtils.isNotBlank(leavebillVo.getContent()), "content", leavebillVo.getContent());
			// ge大于等于 lte、le 小于等于
			queryWapper.ge(leavebillVo.getStartTime() != null, "leavetime", leavebillVo.getStartTime());
			queryWapper.le(leavebillVo.getEndTime() != null, "leavetime", leavebillVo.getEndTime());
		}
		this.leavebillService.page(page, queryWapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 根据ID查询请假单
	 */
	/*
	 * 根据id查询请假单
	 */
	@RequestMapping("loadLeavebillById")
	public Leavebill loadLeavebillById(Integer id) {
		return this.leavebillService.getById(id);
	}

	/**
	 * 添加请假单
	 */
	@RequestMapping("addLeavebill")
	public ResultObj addLeavebill(LeavebillVo leavebillVo) {
		try {
			this.leavebillService.save(leavebillVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}

	/**
	 * 添加请假单
	 */
	@RequestMapping("updateLeavebill")
	public ResultObj updateLeavebill(LeavebillVo leavebillVo) {
		try {
			this.leavebillService.updateById(leavebillVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}

	}

	/**
	 * 删除请假单
	 */
	@RequestMapping("deleteLeavebill")
	public ResultObj deleteLeavebill(Integer id) {
		try {
			this.leavebillService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

	/**
	 * 批量删除请假单
	 */
	@RequestMapping("batchDeleteLeavebill")
	public ResultObj batchDeleteLeavebill(LeavebillVo leavebillVo) {
		try {
			Collection<Serializable> idList = new ArrayList<>();
			for (Integer id : leavebillVo.getIds()) {
				idList.add(id);
			}
			this.leavebillService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

}
