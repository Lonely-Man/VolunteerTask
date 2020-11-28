package com.sxt.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.controller.response.TaskResponse;
import com.sxt.sys.domain.Task;
import com.sxt.sys.domain.TaskToVolunteer;
import com.sxt.sys.mapper.TaskMapper;
import com.sxt.sys.mapper.TaskToVolunteerMapper;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.request.AppTaskRequest;
import com.sxt.sys.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:07
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskToVolunteerMapper taskToVolunteerMapper;
    @Override
    public void saveTask(Task task) {
        if(task.getId()==null){
            taskMapper.insert(task);
        }else {
            QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id");
            queryWrapper.eq("id",task.getId());
            Task taskDb = taskMapper.selectOne(queryWrapper);
            taskDb.copyParam(task);
            taskMapper.updateById(taskDb);
        }
    }

    @Override
    public boolean isSignUp(String openId, long taskId) {
        List<TaskToVolunteer> taskToVolunteer = taskToVolunteerMapper.selectByOpenIdAndTaskId(openId,taskId);
      if(taskToVolunteer==null||taskToVolunteer.isEmpty()){
          return false;

      }
        return true;
    }

    @Override
    public void signUp(String openId, long taskId) {
        TaskToVolunteer taskToVolunteer = TaskToVolunteer.builder().openId(openId).taskId(taskId).build();
        taskToVolunteerMapper.insert(taskToVolunteer);
    }

    @Override
    public List<TaskResponse> searchByAppTaskReq(AppTaskRequest request) {
        return taskMapper.searchByAppTaskReq(request);
    }

    @Override
    public List<TaskResponse> searchMyTask(String openId) {
        AppTaskRequest request=new AppTaskRequest();
        request.setOpenId(openId);
        request.setSignUp(true);
        request.setChecked(true);
        List<TaskResponse> tasks = taskMapper.searchByAppTaskReq(request);
        tasks.stream().filter(k->k.getEndDate().getTime()<new Date().getTime()).forEach(k->k.setEnd(true));
        return tasks;
    }

    @Override
    public List<TaskResponse> searchNewTask(String openId) {
        AppTaskRequest request=new AppTaskRequest();
        request.setOpenId(openId);
        request.setEnd(false);
        request.setSignUp(false);
        List<TaskResponse> tasks = taskMapper.searchByAppTaskReq(request);
        request.setSignUp(true);
        request.setChecked(false);
        List<TaskResponse> myTask = taskMapper.searchByAppTaskReq(request);
        myTask.forEach(k->k.setAdd(true));
        tasks.addAll(myTask);
        return tasks;
    }
}
