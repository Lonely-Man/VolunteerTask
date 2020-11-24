package com.sxt.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WangEditor {
	private Integer errno; // 错误代码，0 表示没有错误。
	private String[] data; // 已上传的图片路径
}