package com.hang.core.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hang.core.entity.Bookset;

import com.hang.core.service.BooksetService;
import com.hang.core.util.ResponseUtil;

@Controller
public class BooksetController {
	
	private static Logger log = Logger.getLogger(BooksetController.class);
	
	@Autowired
	BooksetService booksetService;
	
	/* 
	 * function select all bookset 
	 * return List<bookset>
	 */
	@RequestMapping(value="/selectbookset",method=RequestMethod.POST)
	public @ResponseBody String selectbookset(short userid,HttpServletResponse response) throws Exception{
		//get booksetList
		List<Bookset> booksetList = booksetService.listBooksetByUserId(userid);
		JSONObject json = new JSONObject();
		if(booksetList != null && !booksetList.isEmpty()){
			JSONArray arrayjson = JSONArray.fromObject(booksetList);
			json.put("BooksetList", arrayjson);
		}
		ResponseUtil.write(response, json);
		log.info("request:list/bookset,userid:"+userid);
		return null;
	}
	/*
	 * function add bookset
	 * return Bookset OR null
	 */
	@RequestMapping(value="/addBookset",method=RequestMethod.POST)  
	public @ResponseBody String addBookset(Bookset bookset
			,HttpServletResponse response) throws Exception{
		JSONObject json = new JSONObject();
		Bookset newBookset = booksetService.insertAndReturnForBookset(bookset);		
		if(newBookset != null)
			json.put("success", newBookset);
		else{
			json.put("sucess", null);
			log.error("/deletebookset exception : ÕËÌ×ÔöÌíÊ§°Ü");
		}
		ResponseUtil.write(response,json);
		log.info("request:save/bookset,bookset:"+bookset.toString());
		return null;
	}
	/*
	 *  delete bookset
	 *  return Boolean
	 */
	@RequestMapping(value="/deletebookset" ,method=RequestMethod.POST)
	public @ResponseBody String deletebookset(HttpServletResponse response,short booksetid) throws Exception{
		JSONObject json = new JSONObject();
		int a = booksetService.deleteByPrimaryKey(booksetid);
		if(a == 1)
			json.put("result", true);
		else{
			json.put("result", false); 
			log.error("/deletebookset exception : ÕËÌ×É¾³ýÊ§°Ü");
		}
		ResponseUtil.write(response, json);
		log.info("request:delete/bookset,booksetid:"+booksetid);
		return null;
	}

}
