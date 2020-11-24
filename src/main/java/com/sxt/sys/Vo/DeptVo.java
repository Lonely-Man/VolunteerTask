package com.sxt.sys.Vo;

import com.sxt.sys.domain.Dept;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

	private static final long serialVersionUID = 1L;

	// 分页参数
	private Integer page = 1;
	private Integer limit = 10;

}
