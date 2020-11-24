package com.sxt.sys.controller;

import com.sxt.sys.Request.VolunteerQueryRequest;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.domain.Volunteer;
import com.sxt.sys.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:52
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    @Autowired
   private VolunteerService volunteerService;
    @PostMapping("/list")
    public DataGridView loadAllRole(@RequestBody VolunteerQueryRequest volunteerQueryRequest) {
        List<Volunteer> volunteerByRequest = volunteerService.findVolunteerByRequest(volunteerQueryRequest);
        long count = volunteerService.countVolunteerByRequest(volunteerQueryRequest);
        return new DataGridView(count, volunteerByRequest);

    }
}
