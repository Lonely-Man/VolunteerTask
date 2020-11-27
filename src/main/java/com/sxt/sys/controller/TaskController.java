package com.sxt.sys.controller;

import com.sxt.sys.common.DataGridView;
import com.sxt.sys.domain.Task;
import com.sxt.sys.request.AppTaskRequest;
import com.sxt.sys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:06
 */
@RestController
@RequestMapping("app/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 活动报名
      * @param openId
     * @param taskId
     * @return
     */
    @PostMapping("/signUp")
    public DataGridView save(@RequestParam("openId") String openId,@RequestParam("taskId") String taskId) {
        boolean isSignUp = taskService.isSignUp(openId, taskId);
        if(isSignUp){
            return DataGridView.builder().success(false).msg("您已经报过名了").build();
        }
        taskService.isSignUp(openId,taskId);
        return DataGridView.builder().success(true).msg("报名成功").build();
    }

    /**
     * 活动查询
     * @param request
     * @return
     */
    @PostMapping("/list")
    public DataGridView searchByReq(@RequestBody AppTaskRequest request) {
        List<Task> tasks = taskService.searchByAppTaskReq(request);
        return DataGridView.builder().success(true).msg("成功").data(tasks).build();
    }
}
