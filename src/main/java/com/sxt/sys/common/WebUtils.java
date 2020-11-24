package com.sxt.sys.common;

import com.sun.xml.internal.ws.client.ResponseContext;
import com.sun.xml.internal.ws.client.ResponseContextReceiver;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtils {
    /**
     * 等到request
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }
    /**
     * 得到session
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

}
