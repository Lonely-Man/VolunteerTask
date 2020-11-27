package com.sxt.sys.controller;

import com.sxt.sys.common.DataGridView;
import com.sxt.sys.domain.Task;
import com.sxt.sys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/27 17:08
 */
@RestController
@RequestMapping("web/task")
public class TaskWebController {
    @Autowired
    private TaskService taskService;

    /**
     * 活动保存 新增 编辑
     * @param task
     * @return
     */
    @PostMapping("/save")
    public DataGridView save(@RequestBody @Valid Task task) {
        taskService.saveTask(task);
        return DataGridView.builder().success(true).msg("成功").build();
    }
}
