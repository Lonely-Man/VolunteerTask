package com.sxt.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.Loginfo;
import com.sxt.sys.mapper.LoginfoMapper;
import com.sxt.sys.service.LoginfoService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

}
