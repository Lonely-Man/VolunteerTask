package com.sxt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.sys.domain.Volunteer;

import java.util.List;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 15:03
 */
public interface VolunteerMapper extends BaseMapper<Volunteer> {


   void checkedVolunteersById(List<Integer> ids);
}
