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

import com.hang.core.entity.Voucher_It;
import com.hang.core.service.Voucher_ItService;
import com.hang.core.util.ResponseUtil;

@Controller
public class Voucher_ItController {
	
	private static Logger log = Logger.getLogger(Voucher_ItController.class);
	
	@Autowired
	Voucher_ItService itService;
	
	@RequestMapping(value = "/queryVoucher_It",method=RequestMethod.POST)
	public String queryVoucher_It(Short voucherid,HttpServletResponse response) throws Exception{
		List<Voucher_It> list = itService.getVoucher_ItByVoucherId(voucherid);
		JSONObject json = new JSONObject();
		if(list != null && !list.isEmpty()) {
			JSONArray arr = JSONArray.fromObject(list);
			json.put("Voucher_ItList", arr);
		}
		ResponseUtil.write(response, json);
		log.info("list/voucherit, voucherid"+voucherid);
		return null;
	}
}
