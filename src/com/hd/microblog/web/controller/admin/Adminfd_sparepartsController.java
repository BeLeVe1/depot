package com.hd.microblog.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.microblog.model.dt_admin;
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.fd_spareparts;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.fd_sparepartsService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class Adminfd_sparepartsController {
	 
	@Autowired
	@Qualifier("fd_sparepartsService")
	private fd_sparepartsService fd_sparepartsservice;
	
	//预测查询表
	@RequestMapping("/adminfd_sparepartslist")
	public String adminfd_sparepartslist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_sparepartslist";
	}
	
  //列表
		@RequestMapping(value = "/adminfd_sparepartslistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminfd_sparepartslistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw 
			) throws IOException {
			
			Enumeration enu=request.getParameterNames();  
			while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			
			}
			Integer start = Integer.valueOf(request.getParameter("start"));  
		    String length = request.getParameter("length");  
		    String sparepartsName = request.getParameter("sparepartsName");  
		    int number = Integer.valueOf(length);
			List items = fd_sparepartsservice.adminfindfd_sparepartslist(start,number);
			List count = fd_sparepartsservice.adminfindfd_sparepartslistcount(sparepartsName);
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
	
		//搜索
		@RequestMapping(value = "/adminfd_sparepartslist1ajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminmachinelistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
				String jqcode,String bdcode) throws IOException {
			
			Integer start = Integer.valueOf(request.getParameter("start"));
		    String length = request.getParameter("length");  
			int number = Integer.valueOf(length);
			List items = fd_sparepartsservice.adminfindfd_sparepartslist1(jqcode,bdcode,start,number);
			List count = fd_sparepartsservice.adminfindfd_sparepartslistcount1(jqcode,bdcode);
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
	
	//信息修改
	@RequestMapping("/adminfd_sparepartsreset")
	public String adminfd_sparepartsreset(HttpServletRequest request, HttpServletResponse resp,
			Integer sparepartsNum) 
			throws IOException {
		fd_spareparts dataprocess = fd_sparepartsservice.get(sparepartsNum);
		request.setAttribute("sparepartsNum", sparepartsNum);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/fd_sparepartsreset";
	}
	
	//信息添加
	@RequestMapping("/adminfd_sparepartsadd")
	public String adminfd_sparepartsadd(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_sparepartsadd";
	}
	
	@RequestMapping(value = "/adminfd_sparepartsresetajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_sparepartsresetajax(HttpServletRequest request,int sparepartsNum, String sparepartsType,String sparepartsName, String sparepartsSpecification,int sparepartsStocks,String sparepartsUnit,double sparepartsPrice) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			fd_spareparts  dataprocess = fd_sparepartsservice.get(sparepartsNum);
	        dataprocess.setSparepartsNum(sparepartsNum);
	        dataprocess.setSparepartsType(sparepartsType);
	        dataprocess.setSparepartsName(sparepartsName);
	        dataprocess.setSparepartsSpecification(sparepartsSpecification);
	        dataprocess.setSparepartsStocks(sparepartsStocks);
	        dataprocess.setSparepartsName(sparepartsUnit);
	        dataprocess.setSparepartsPrice(sparepartsPrice);
	     
	        
	        fd_sparepartsservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	//删除
		@RequestMapping(value = "/adminfd_sparepartsdeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private Map admindeleteajax1(HttpServletRequest request,Integer id) throws IOException {
			Map map = new HashMap<String, String>();
			try{
				if(id==1){
					map.put("code", "200");
				}else{
					fd_spareparts  dataprocess = fd_sparepartsservice.get(id);
					fd_sparepartsservice.deleteObject(dataprocess);
					map.put("code", "100");
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("code", "400");
			}
			return map;
		}
	//增加新
	@RequestMapping(value = "/adminfd_sparepartsaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_sparepartsaddajax(HttpServletRequest request,int sparepartsNum, String sparepartsType,String sparepartsName, String sparepartsSpecification,int sparepartsStocks,String sparepartsUnit,double sparepartsPrice) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			
			List count = fd_sparepartsservice.adminfindaccount(sparepartsNum,sparepartsType,sparepartsName,sparepartsSpecification,sparepartsStocks,sparepartsUnit,sparepartsPrice);
			Map map0 = (Map) count.get(0);
			int countnumber = 0;
			countnumber = Integer.valueOf(String.valueOf(map0.get("count")));
			
			System.out.println(countnumber);
			if(countnumber!= 0){
				map.put("code", "300");
				map.put("info", "已存在");
			}else{
				fd_spareparts  dataprocess = new fd_spareparts();
				dataprocess.setSparepartsNum(sparepartsNum);
				dataprocess.setSparepartsType(sparepartsType);
				dataprocess.setSparepartsName(sparepartsName);
				dataprocess.setSparepartsSpecification(sparepartsSpecification);
				dataprocess.setSparepartsStocks(sparepartsStocks);
				dataprocess.setSparepartsUnit(sparepartsUnit);
		        dataprocess.setSparepartsPrice(sparepartsPrice);
		        
		        fd_sparepartsservice.saveOrUpdate(dataprocess);
		        
				map.put("code", "100");
				map.put("info", "添加成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "添加失败");
		}
		return map;
	}
	
	//Excel生成
	@RequestMapping(value = "/adminfd_sparepartslistExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_sparepartslistExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("备品备件编号");
			header.add("备品备件种类");
			header.add("备品备件名称");
			header.add("备品备件型号");
			header.add("备品备件库存");
			header.add("备品备件计量单位");
			header.add("备品备件单价");
			
		    
		    
		    List items = fd_sparepartsservice.adminfindfd_sparepartslist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("sparepartsNum")));
				data.add(String.valueOf(map.get("sparepartsType")));
				data.add(String.valueOf(map.get("sparepartsName")));
				data.add(String.valueOf(map.get("sparepartsSpecification")));
				data.add(String.valueOf(map.get("sparepartsStocks")));
				data.add(String.valueOf(map.get("sparepartsUnit")));
				data.add(String.valueOf(map.get("sparepartsPrice")));
				
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"机器信息表.xls")){
				createxls.generateExcel("Sheet1", header, body, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			json.put("code", "100");
			json.put("info", "生成成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}
	
	
}






