package com.sxt.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.NoticeVo;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.common.WebUtils;
import com.sxt.sys.domain.Notice;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.NoticeService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/**
	 * 加载所有公告
	 * @param noticeVo
	 * @return
	 */
	@RequestMapping("loadAllNotice")
	public DataGridView loadAllNotice(NoticeVo noticeVo) {
		// 查询条件
		IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
		QueryWrapper<Notice> queryWapper = new QueryWrapper<>();
		queryWapper.like(StringUtils.isNotBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
		queryWapper.like(StringUtils.isNotBlank(noticeVo.getOpername()), "opername", noticeVo.getOpername());
		// ge大于等于 lte、le 小于等于
		queryWapper.ge(noticeVo.getStartTime() != null, "createtime", noticeVo.getStartTime());
		queryWapper.le(noticeVo.getEndTime() != null, "createtime", noticeVo.getEndTime());
		// 倒序
		queryWapper.orderByDesc("createtime");
		this.noticeService.page(page, queryWapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 根据ID查询公告
	 */
	/*
	 * 根据id查询公告
	 */
	@RequestMapping("loadNoticeById")
	public Notice loadNoticeById(Integer id) {
		return this.noticeService.getById(id);
	}

	/**
	 * 添加公告
	 */
	@RequestMapping("addNotice")
	public ResultObj addNotice(NoticeVo noticeVo) {
		try {
			noticeVo.setCreatetime(new Date());
			User user = (User) WebUtils.getSession().getAttribute("user");
			noticeVo.setOpername(user.getName());
			this.noticeService.save(noticeVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}

	/**
	 * 更改公告
	 */
	@RequestMapping("updateNotice")
	public ResultObj updateNotice(NoticeVo noticeVo) {
		try {
			this.noticeService.updateById(noticeVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}

	}

	/**
	 * 删除公告
	 */
	@RequestMapping("deleteNotice")
	public ResultObj deleteNotice(Integer id) {
		try {
			this.noticeService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

	/**
	 * 批量删除公告
	 */
	@RequestMapping("batchDeleteNotice")
	public ResultObj batchDeleteNotice(NoticeVo noticeVo) {
		try {
			Collection<Serializable> idList = new ArrayList<>();
			for (Integer id : noticeVo.getIds()) {
				idList.add(id);
			}
			this.noticeService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

}
