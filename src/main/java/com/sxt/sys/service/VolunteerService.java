package com.sxt.sys.service;

import com.sxt.sys.request.VolunteerQueryRequest;
import com.sxt.sys.domain.Volunteer;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:53
 */
public interface VolunteerService {
    List<Volunteer> findVolunteerByRequest(VolunteerQueryRequest request);
    long countVolunteerByRequest(VolunteerQueryRequest request);

    void   updateById(Volunteer volunteer);
    void checkVolunteers(List<Integer> ids);
    Volunteer findByOpenId(String openId);

    /**
     * 保存微信公众号来的志愿者信息
     * @param volunteer
     */
    void saveVolunteer(Volunteer volunteer);
}
