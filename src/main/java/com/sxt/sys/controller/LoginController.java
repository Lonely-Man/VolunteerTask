package com.sxt.sys.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.common.ActiverUser;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.common.WebUtils;
import com.sxt.sys.domain.Loginfo;
import com.sxt.sys.service.LoginfoService;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-07-13
 */
@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	private LoginfoService loginfoService;

	@RequestMapping("login")
	public ResultObj login(String loginname, String pwd,boolean rememberme,HttpServletResponse response) throws UnknownHostException {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);

		try {

			subject.login(token);
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user", activerUser.getUser());
			Cookie cookie = null;
			if (rememberme!=false){
				cookie = new Cookie("loginname", loginname);
				//把账号存入Cookie且名字为username
				//设置过期时间（以秒为单位）
				cookie.setMaxAge(3000);
				//设置添加到根路径下
				cookie.setPath("/");
				//添加Cookie
				response.addCookie(cookie);

			}else{
				//删除cookie
				cookie = new Cookie("loginname", null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			// 记录登录日志

			Loginfo entity = new Loginfo();
			entity.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());

			InetAddress addr;
			addr = (InetAddress) InetAddress.getLocalHost();
			// 获取内网Id地址
			entity.setLoginip(addr.getHostAddress().toString());
			entity.setLogintime(new Date());
			loginfoService.save(entity);
			return ResultObj.LOGIN_SUCCESS;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}
	}

	/**
	 * 得到登录验证码
	 * @throws IOException 
	 */
	@RequestMapping("getCode")
	public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 30);
		Console.log(lineCaptcha.getCode());
		session.setAttribute("code", lineCaptcha.getCode());
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);
	}
}
