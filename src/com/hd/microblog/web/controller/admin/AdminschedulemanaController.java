package com.hd.microblog.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.microblog.model.dt_admin;
import com.hd.microblog.model.dt_realschedule;
import com.hd.microblog.model.dt_schedulemana;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_schedulemanaService;
import com.hd.microblog.util.createxls;

@Controller
public class AdminschedulemanaController {
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_schedulemanaService")
	private dt_schedulemanaService dt_schedulemanaservice;
	
	//调度方案管理
	@RequestMapping("/adminschedulemana")
	public String adminschedulemana(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/schedulemana";
	}
	//调度方案信息表
	@RequestMapping(value = "/adminschedulemanaajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminschedulemanaajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String schedule_number,String orcode,String gjcode,String opcode) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_schedulemanaservice.adminfindschedulemanalist(schedule_number,orcode,gjcode,opcode,start,number);
		List count = dt_schedulemanaservice.adminfindschedulemanalistcount(schedule_number,orcode,gjcode,opcode);
		System.out.println(count);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count(distinct schedule_number)")));
		}
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			
			returnlist.add(map);
		}
		
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}
	
	//调度方案修改
	@RequestMapping("/adminschedulemanadetailedit")
	public String adminschedulemanadetailedit(HttpServletRequest request, HttpServletResponse resp, Integer schedulemana_id) 
			throws IOException {
		dt_schedulemana  dataprocess = dt_schedulemanaservice.get(schedulemana_id);
		request.setAttribute("schedulemana_id", schedulemana_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/schedulemanadetailedit";
	}
	
	@RequestMapping(value = "/adminschedulemanadetaileditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminschedulemanadetaileditajax(HttpServletRequest request,Integer schedulemana_id,String sttime, String edtime) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		sttime = sttime.replace("T"," ");
		edtime = edtime.replace("T"," ");
		try{        
			dt_schedulemana  dataprocess = dt_schedulemanaservice.get(schedulemana_id);
	        dataprocess.setsttime(sttime);
	        dataprocess.setedtime(edtime);
	        dt_schedulemanaservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	
	//调度结果显示
	@RequestMapping("/adminfigureshow")
	public String adminfigureshow(HttpServletRequest request, HttpServletResponse resp, String figuredata_id) 
			throws IOException {
		request.setAttribute("figuredata_id", figuredata_id);
		return "admin/figureshow";
	}

	//投料计划制定，当前计划内容
	@RequestMapping("/adminschedulemanadetail")
	public String adminschedulemanadetail(HttpServletRequest request, HttpServletResponse resp,
			String schedule_number) 
			throws IOException {
		request.setAttribute("schedule_number", schedule_number);
		return "admin/schedulemanadetail";
	}
	
	//工件信息表
	@RequestMapping(value = "/adminschedulemanadetailajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminschedulemanadetailajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String schedule_number,String orcode,String gjcode,String opcode) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_schedulemanaservice.adminfindschedulemanadetaillist(schedule_number,orcode,gjcode,opcode,start,number);
		List count = dt_schedulemanaservice.adminfindschedulemanadetaillistcount(schedule_number,orcode,gjcode,opcode);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			
			returnlist.add(map);
		}
		
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}
}


