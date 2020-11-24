package com.sxt.sys.service.impl;

import com.sxt.sys.Request.VolunteerQueryRequest;
import com.sxt.sys.domain.Volunteer;
import com.sxt.sys.mapper.VolunteerMapper;
import com.sxt.sys.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 15:04
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Resource
    private VolunteerMapper volunteerMapper;
    @Override
    public List<Volunteer> findVolunteerByRequest(VolunteerQueryRequest request) {
        return null;
    }

    @Override
    public long countVolunteerByRequest(VolunteerQueryRequest request) {
        return 0;
    }

    @Override
    public void updateById(Volunteer volunteer) {
      volunteerMapper.updateById(volunteer);
    }

    @Override
    public void checkVolunteers(List<Integer> ids) {
     volunteerMapper.checkedVolunteersById(ids);
    }
}
