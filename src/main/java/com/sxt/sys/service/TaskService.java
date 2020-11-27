package com.sxt.sys.service;

import com.sxt.sys.domain.Task;
import com.sxt.sys.domain.Volunteer;
import com.sxt.sys.request.AppTaskRequest;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:07
 */
public interface TaskService {
    /**
     * 保存活动
     * @param task
     */
    void saveTask(Task task);
    boolean isSignUp(String openId,String taskId);
    void signUp(String openId,String taskId);
    List<Task> searchByAppTaskReq(AppTaskRequest request);
}
