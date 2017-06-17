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

import com.hang.core.entity.Voucher;
import com.hang.core.entity.VoucherKey;
import com.hang.core.pojo.VoucherKeyPlus;
import com.hang.core.pojo.VoucherRecord;
import com.hang.core.pojo.VoucherSummaryVo;
import com.hang.core.pojo.Voucher_ItRecord;
import com.hang.core.service.VoucherService;
import com.hang.core.util.ResponseUtil;

@Controller
public class VoucherController {
	
	private static Logger log = Logger.getLogger(VoucherController.class);
	@Autowired
	VoucherService voucherService;
	
	@RequestMapping(value="/queryNumOfVoucher",method=RequestMethod.POST)
	public String queryNumOfVoucher(HttpServletResponse response,VoucherKey key) throws Exception{
		int num = voucherService.getMAXValueOfItemId(key);
		JSONObject json = new JSONObject();
		json.put("num", num);
		ResponseUtil.write(response, json);
		log.info("count/voucher, voucherkey:"+key.toString());
		return null;
	}
	
	@RequestMapping(value="/submitVoucher",method=RequestMethod.POST)
	public String submitVoucher(@RequestBody VoucherRecord voucherRecord,HttpServletResponse response) throws Exception{
		JSONObject json = new JSONObject();
		if(voucherService.insertVoucher(voucherRecord)){
			json.put("success", true);
		}else{
			json.put("success",false);
			log.error("/submitVoucher exception : 凭证增添失败");
		}
		ResponseUtil.write(response, json);
		log.info("save/voucher, voucherdetail:"+voucherRecord);
		return null;
	}
	
	@RequestMapping(value="/queryAccountPeriod",method=RequestMethod.POST)
	public String queryAccountPeriod(Short booksetid,HttpServletResponse response) throws Exception{
		JSONObject json = new JSONObject();
		List<VoucherKey> list = voucherService.selectAllVoucherKey(booksetid);
		if(list!=null&&!list.isEmpty()){
		JSONArray arr = JSONArray.fromObject(list);
		json.put("accPerList", arr);
		}
		ResponseUtil.write(response, json);
		log.info("list/accountperiod, booksetid"+booksetid);
		return null;
	}
	
	@RequestMapping(value="/queryVoucherTable",method=RequestMethod.POST)
	public String queryVoucherTable( VoucherKey voucherkey,HttpServletResponse response) throws Exception{
		List<Voucher_ItRecord> recordList = voucherService.selectAllVoucher(voucherkey);
		JSONObject json = new JSONObject();
		if(recordList != null && !recordList.isEmpty()) {
			JSONArray arr= JSONArray.fromObject(recordList);
			json.put("AllVoucher", arr);
		}
		ResponseUtil.write(response, json);
		log.info("list/all voucher, voucherkey:"+voucherkey.toString());
		return null;
	}
	
	@RequestMapping(value="/updateVoucher",method=RequestMethod.POST)
	public String updateVoucher(@RequestBody VoucherRecord voucherRecord,HttpServletResponse response) throws Exception{
		JSONObject  json = new JSONObject();
		if(voucherService.updateVoucher(voucherRecord)){
			json.put("status", true);
		}else{
			log.error("/updateVoucher exception : 凭证更新失败");
			json.put("status", false);
		}
		ResponseUtil.write(response, json);
		log.info("update/voucher, newvoucher:"+voucherRecord.toString());
		return null;
	}
	
	@RequestMapping(value="/deleteVoucher",method=RequestMethod.POST)
	public String deleteVoucher(@RequestBody Voucher voucher, HttpServletResponse response) throws Exception{
		JSONObject json = new JSONObject();
		if(voucherService.deleteVoucher(voucher)){
			json.put("status", true);
		}else{
			json.put("status", false);
			log.error("/deleteVoucher exception : 凭证更新删除失败");
		}
		ResponseUtil.write(response, json);
		log.info("delete/voucher, voucher:"+voucher);
		return null;
	}
	
	
	@RequestMapping(value="/listVoucherItemForSummary",method=RequestMethod.POST)
	public String listVoucherItemForSummary( VoucherKeyPlus key, HttpServletResponse response) throws Exception{
		JSONObject json = new JSONObject();
		List<VoucherSummaryVo> list =  voucherService.listVoucherSummaryVo(key);
		if(list != null && !list.isEmpty()){
			JSONArray arr = JSONArray.fromObject(list);
			json.put("listVoucherItem",arr);
		}
		ResponseUtil.write(response, json);
		log.info("list/vouchersummarytable, voucherkey:"+key);
		return null;
	}
}
