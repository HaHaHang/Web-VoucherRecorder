package com.hang.core.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.hang.core.entity.User;

import com.hang.core.pojo.InitUserInfo;
import com.hang.core.service.LoginService;
import com.hang.core.util.ResponseUtil;

@Controller
public class LoginController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;

	//登陆模块:验证用户
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String main(User user,HttpServletRequest request,HttpServletResponse response,
			final RedirectAttributes attributes) throws Exception{
		//用户的ID号
		short userid = loginService.getUserIdByUnAndPw(user);
		JSONObject json = new JSONObject();
		if(userid > 0){//ID存在:登陆成功
			//配置环境变量userid
			request.getSession().setAttribute("userid", userid);
			json.put("result", true);
			//重定向
		}else{//ID不存在:登陆失败
			//登陆失败情况 
			json.put("result", false);
		}
		log.info("request:login");
		ResponseUtil.write(response, json);
		return null;
	}
	//登陆模块:加载信息
	@RequestMapping(value="/Main")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//Main页面
		short userid = (Short) request.getSession().getAttribute("userid");
		HttpSession session = request.getSession();
		//获取init对象
		InitUserInfo initUserInfo =  loginService.initUserInfo(userid);
		if(initUserInfo != null){
			//存入用户信息
			session.setAttribute("UserInfo", initUserInfo);
		}else{
			log.error("/Main exception : 用户信息存入失败 ( inituserinfo = null )");
		}
		log.info("request:Main");
		return "Main";
	}
	//注销账户
	@RequestMapping("/logout")
	public String Logout(HttpSession session){
		//清空session
		session.invalidate();
		log.info("request:logout;");
		return "redirect:/Login.jsp";
	}
	
}
