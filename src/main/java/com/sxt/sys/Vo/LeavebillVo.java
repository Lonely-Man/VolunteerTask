package com.sxt.sys.Vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sxt.sys.domain.Leavebill;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LeavebillVo extends Leavebill {

	private static final long serialVersionUID = 1L;

	// 分页参数
	private Integer page = 1;
	private Integer limit = 10;
	// 接收多个Id 用于批量删除
	private Integer[] ids;

	// 查询时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

}
