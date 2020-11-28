package com.sxt.sys.controller;

import com.sxt.sys.common.DataGridView;
import com.sxt.sys.controller.request.SignUpRequest;
import com.sxt.sys.controller.response.TaskResponse;
import com.sxt.sys.request.AppTaskRequest;
import com.sxt.sys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:06
 */
@RestController
@RequestMapping("/app/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 活动报名
     * @return
     */
    @PostMapping("/signUp")
    public DataGridView save(@RequestBody SignUpRequest signUpRequest) {

        boolean isSignUp = taskService.isSignUp(signUpRequest.getOpenId(), signUpRequest.getTaskId());
        if(isSignUp){
            return DataGridView.builder().success(false).msg("您已经报过名了").build();
        }
        taskService.signUp(signUpRequest.getOpenId(),signUpRequest.getTaskId());
        List<TaskResponse> tasks = taskService.searchNewTask(signUpRequest.getOpenId());
        return DataGridView.builder().data(tasks).success(true).msg("报名成功").build();
    }


    @GetMapping("/myTask")
    public DataGridView searchByReq(@RequestParam("openId") String openId) {
        System.out.println("openid**********"+openId);
        List<TaskResponse> tasks = taskService.searchMyTask(openId);
        return DataGridView.builder().success(true).msg("成功").data(tasks).build();
    }
    @GetMapping("/newTask")
    public DataGridView searchByNew(@RequestParam String openId) {
        System.out.println("openid**********"+openId);
        List<TaskResponse> tasks = taskService.searchNewTask(openId);
        return DataGridView.builder().success(true).msg("成功").data(tasks).build();
    }
    @PostMapping("/openId")
    public DataGridView search(@RequestParam String openId) {
        AppTaskRequest a=new AppTaskRequest();
        a.setOpenId(openId);
        List<TaskResponse> tasks = taskService.searchByAppTaskReq(a);
        return DataGridView.builder().success(true).msg("成功").data(tasks).build();
    }
}
