package com.sxt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.sys.domain.User;

/**
 * <p>
 *  服务类
 * </p>

 */
public interface UserService extends IService<User> {
//    保存用户和角色之间的关系
	void saveUserRole(Integer uid, Integer[] ids);

    Boolean queryMgrByUserId(Integer userId);
}
