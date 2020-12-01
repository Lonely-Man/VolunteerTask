package com.sxt.sys.controller;

import com.sxt.sys.common.DataGridView;
import com.sxt.sys.domain.Volunteer;
import com.sxt.sys.request.VolunteerQueryRequest;
import com.sxt.sys.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:52
 */
@RestController
@RequestMapping("web/volunteer")
public class VolunteerWebController {
    @Autowired
   private VolunteerService volunteerService;
    @PostMapping("/list")
    public DataGridView loadAllRole(@RequestBody VolunteerQueryRequest volunteerQueryRequest) {
        List<Volunteer> volunteerByRequest = volunteerService.findVolunteerByRequest(volunteerQueryRequest);
        long count = volunteerService.countVolunteerByRequest(volunteerQueryRequest);
        return new DataGridView(count, volunteerByRequest);

    }
    /**
     * 活动保存 新增 编辑
     * @param volunteer
     * @return
     */
    @RequestMapping("/save")
    public DataGridView save(@Valid Volunteer volunteer) {
        volunteerService.saveVolunteer(volunteer);
        return DataGridView.builder().success(true).msg("成功").build();
    }
}
