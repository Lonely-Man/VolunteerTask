package com.sxt.sys.Vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkFlowVo {

	private static final long serialVersionUID = 1L;

	// 分页参数
	private Integer page = 1;
	private Integer limit = 10;
	// 接收多个Id 用于批量删除
	private Integer[] ids;

	// 流程部署名称
	private String deploymentName;

	// 流程部署ID
	private String deploymentId;
	// 请假单ID
	private Integer id;
	// 任务ID
	private String taskId;
	// 连线名称
	private String outcome;
	// 批注信息
	private String comment;
	// 流程实例ID
	private String processInstanceId;

	// 查询时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

}
