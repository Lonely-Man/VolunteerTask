package com.sxt.sys.controller;

import com.sxt.sys.request.VolunteerQueryRequest;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.domain.Volunteer;
import com.sxt.sys.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:52
 */
@RestController
@RequestMapping("app/volunteer")
public class VolunteerController {
    @Autowired
   private VolunteerService volunteerService;
    @PostMapping("/save")
    public DataGridView save(@RequestBody Volunteer volunteer) {
        volunteerService.saveVolunteer(volunteer);
        return DataGridView.builder().success(true).msg("成功").build();
    }
    @GetMapping("/openId")
    public DataGridView save(@RequestParam String openId) {
        Volunteer volunteer = volunteerService.findByOpenId(openId);
        return DataGridView.builder().success(true).msg("成功").data(volunteer).count(1L).build();
    }
}
