package com.sxt.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.Notice;
import com.sxt.sys.mapper.NoticeMapper;
import com.sxt.sys.service.NoticeService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
