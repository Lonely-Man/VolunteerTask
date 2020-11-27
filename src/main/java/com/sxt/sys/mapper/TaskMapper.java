package com.sxt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.sys.domain.Task;
import com.sxt.sys.request.AppTaskRequest;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface TaskMapper extends BaseMapper<Task> {
    List<Task> searchByAppTaskReq(AppTaskRequest request);
}
