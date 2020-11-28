package com.sxt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.sys.domain.TaskToVolunteer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:54
 */
public interface TaskToVolunteerMapper  extends BaseMapper<TaskToVolunteer> {
    List<TaskToVolunteer> selectByOpenIdAndTaskId(@Param("openId") String openId, @Param("taskId")long taskId);
}
