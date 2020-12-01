package com.sxt.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.Vo.TaskVo;
import com.sxt.sys.Vo.UserVo;
import com.sxt.sys.common.Constast;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.domain.Task;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 17:08
 */
@RestController
@RequestMapping("/web/task")
public class TaskWebController {
    @Autowired
    private TaskService taskService;
    @RequestMapping("list")
    public DataGridView loadAllTask(TaskVo taskVo) {
        IPage<Task> page = new Page<>(taskVo.getPage(), taskVo.getLimit());
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(taskVo.getName()), "loginname", taskVo.getName()).or()
                .eq(StringUtils.isNotBlank(taskVo.getName()), "name", taskVo.getName());
        queryWrapper.eq(StringUtils.isNotBlank(taskVo.getAddress()), "address", taskVo.getAddress());
        this.taskService.page(page, queryWrapper);

        System.out.println(taskService.getClass().getSimpleName());
        List<Task> list = page.getRecords();


        return new DataGridView(page.getTotal(), list);

    }

    /**
     * 活动保存 新增 编辑
     * @param task
     * @return
     */
    @RequestMapping("/save")
    public DataGridView save(@Valid Task task) {
        taskService.saveTask(task);
        return DataGridView.builder().success(true).msg("成功").build();
    }
    /**
     * 删除活动
     */
    @RequestMapping("/deleteTask")
    public ResultObj deleteTask(Integer id) {
        try {
            this.taskService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
