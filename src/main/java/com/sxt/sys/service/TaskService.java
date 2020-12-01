package com.sxt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.sys.controller.response.TaskResponse;
import com.sxt.sys.domain.Task;
import com.sxt.sys.request.AppTaskRequest;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:07
 */
public interface TaskService extends IService<Task> {
    /**
     * 保存活动
     * @param task
     */
    void saveTask(Task task);
    boolean isSignUp(String openId,long taskId);
    void signUp(String openId,long taskId);
    void cancelSignUp(String openId,long taskId);
    List<TaskResponse> searchByAppTaskReq(AppTaskRequest request);
    List<TaskResponse> searchMyTask(String openId);
    List<TaskResponse> searchNewTask(String openId);


}
