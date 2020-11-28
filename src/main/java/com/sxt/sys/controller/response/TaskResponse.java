package com.sxt.sys.controller.response;

import com.sxt.sys.domain.Task;
import lombok.Data;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/29 3:20
 */
@Data
public class TaskResponse extends Task {
    private boolean add;
    private boolean end;


}
