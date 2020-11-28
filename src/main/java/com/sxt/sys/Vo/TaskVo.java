package com.sxt.sys.Vo;

import com.sxt.sys.domain.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/29 1:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TaskVo extends Task {
    private static final long serialVersionUID = 1L;

    // 分页参数
    private Integer page = 1;
    private Integer limit = 10;
}
