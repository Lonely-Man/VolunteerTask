package com.sxt.sys.listener;

import com.sxt.Application;
import com.sxt.sys.common.WebUtils;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

public class TaskListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        //得到当前用户
        User user = (User)WebUtils.getSession().getAttribute("user");
        //取出领导ID
        Integer mgr = user.getMgr();
        //取出IOC容器  这个类是acitivti无法用IOC的自动注入
        HttpServletRequest request = WebUtils.getRequest();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        //从IOC容器取出UserService
        UserService userService = applicationContext.getBean(UserService.class);
        //查询领导信息
        User leaderUser = userService.getById(mgr);
        //设置办理人
        delegateTask.setAssignee(leaderUser.getName());
    }
}
