package com.hang.core.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hang.core.entity.Subject_Add;
import com.hang.core.service.SubjectAddService;
import com.hang.core.util.JudgmentUtil;
import com.hang.core.util.ResponseUtil;


@Controller
public class SubjectAddController {

	private static Logger log = Logger.getLogger(SubjectAddController.class);
	@Autowired
	SubjectAddService service;
	
	@RequestMapping(value = "/saveCustomSubject", method = RequestMethod.POST)
	public String saveCustomSubject(HttpServletResponse response, Subject_Add key) throws Exception {
		try {
			service.saveCustomSubject(key);
		} catch (Exception e) {
			log.error("saveCustomSubject fail", e);
		}
		JSONObject json = new JSONObject();
		json.put("status", true);
		log.info("saveCustomSubject success:"+key);
		ResponseUtil.write(response, json);
		return null;
	}
	@RequestMapping(value = "/deleteCustomSubject", method = RequestMethod.POST)
	public String deleteCustomSubject(HttpServletResponse response,Subject_Add key) throws Exception{
		try {
			service.deleteCustomSubject(key);
		} catch (Exception e) {
			log.error("deleteCustomSubject fail", e);
		}
		JSONObject json = new JSONObject();
		json.put("status", true);
		log.info("deleteCustomSubject success"+key);
		ResponseUtil.write(response, json);
		return null;
	}
	
	@RequestMapping(value = "/listAllCustomSubject", method = RequestMethod.POST)
	public String listAllCustomSubject(Short userid, HttpServletResponse response) throws Exception {
		List<Subject_Add> list =  service.listAllCustomSubject(userid);
		JSONObject json = new JSONObject();
		if(JudgmentUtil.collectionIsEmpty(list)){
			json.put("status", false);
		}else{
			JSONArray arr = JSONArray.fromObject(list);
			json.put("status", true);
			json.put("subject", arr);
		}
		log.info("listAllCustomSubject success" + list);
		ResponseUtil.write(response, json);
		return null;
	}
	@RequestMapping(value = "/getCountOfSubjectAdd", method = RequestMethod.POST)
	public String getCountOfSubjectAdd(Subject_Add key, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		if(service.getCountOfSubject(key)) {
			json.put("status", true);
		}else{
			json.put("status", false);
		}
		log.info("getCountOfSubjectAdd ");
		ResponseUtil.write(response, json);
		return null;
	}
}
