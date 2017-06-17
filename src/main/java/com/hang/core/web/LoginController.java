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

	//��½ģ��:��֤�û�
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String main(User user,HttpServletRequest request,HttpServletResponse response,
			final RedirectAttributes attributes) throws Exception{
		//�û���ID��
		short userid = loginService.getUserIdByUnAndPw(user);
		JSONObject json = new JSONObject();
		if(userid > 0){//ID����:��½�ɹ�
			//���û�������userid
			request.getSession().setAttribute("userid", userid);
			json.put("result", true);
			//�ض���
		}else{//ID������:��½ʧ��
			//��½ʧ����� 
			json.put("result", false);
		}
		log.info("request:login");
		ResponseUtil.write(response, json);
		return null;
	}
	//��½ģ��:������Ϣ
	@RequestMapping(value="/Main")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//Mainҳ��
		short userid = (Short) request.getSession().getAttribute("userid");
		HttpSession session = request.getSession();
		//��ȡinit����
		InitUserInfo initUserInfo =  loginService.initUserInfo(userid);
		if(initUserInfo != null){
			//�����û���Ϣ
			session.setAttribute("UserInfo", initUserInfo);
		}else{
			log.error("/Main exception : �û���Ϣ����ʧ�� ( inituserinfo = null )");
		}
		log.info("request:Main");
		return "Main";
	}
	//ע���˻�
	@RequestMapping("/logout")
	public String Logout(HttpSession session){
		//���session
		session.invalidate();
		log.info("request:logout;");
		return "redirect:/Login.jsp";
	}
	
}
