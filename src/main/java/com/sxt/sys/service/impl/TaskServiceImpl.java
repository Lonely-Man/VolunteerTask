package com.sxt.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxt.sys.domain.Task;
import com.sxt.sys.domain.TaskToVolunteer;
import com.sxt.sys.mapper.TaskMapper;
import com.sxt.sys.mapper.TaskToVolunteerMapper;
import com.sxt.sys.request.AppTaskRequest;
import com.sxt.sys.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 16:07
 */
@Service
public class TaskServiceImpl implements TaskService {
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
    public boolean isSignUp(String openId, String taskId) {
        QueryWrapper<TaskToVolunteer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("openId","taskId");
        queryWrapper.eq("openId",openId);
        queryWrapper.eq("taskId",taskId);
        List<TaskToVolunteer> taskToVolunteer = taskToVolunteerMapper.selectList(queryWrapper);
      if(taskToVolunteer==null||taskToVolunteer.isEmpty()){
          return false;

      }
        return true;
    }

    @Override
    public void signUp(String openId, String taskId) {
        TaskToVolunteer taskToVolunteer = TaskToVolunteer.builder().openId(openId).taskId(taskId).build();
        taskToVolunteerMapper.insert(taskToVolunteer);
    }

    @Override
    public List<Task> searchByAppTaskReq(AppTaskRequest request) {
        return taskMapper.searchByAppTaskReq(request);
    }
}
