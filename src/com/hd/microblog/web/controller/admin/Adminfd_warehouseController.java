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
import com.hd.microblog.model.fd_warehouse;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.fd_warehouseService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class Adminfd_warehouseController {
	 
	@Autowired
	@Qualifier("fd_warehouseService")
	private fd_warehouseService fd_warehouseservice;
	
	//预测查询表
	@RequestMapping("/adminfd_warehouselist")
	public String adminfd_warehouselist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_warehouselist";
	}
	
  //列表
		@RequestMapping(value = "/adminfd_warehouselistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminfd_warehouselistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw 
			) throws IOException {
			
			Enumeration enu=request.getParameterNames();  
			while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			
			}
			Integer start = Integer.valueOf(request.getParameter("start"));  
		    String length = request.getParameter("length");  
		    String warehouseName = request.getParameter("warehouseName");  
		    int number = Integer.valueOf(length);
			List items = fd_warehouseservice.adminfindfd_warehouselist(start,number);
			List count = fd_warehouseservice.adminfindfd_warehouselistcount(warehouseName);
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
		@RequestMapping(value = "/adminfd_warehouselist1ajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminmachinelistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
				String jqcode,String bdcode) throws IOException {
			
			Integer start = Integer.valueOf(request.getParameter("start"));
		    String length = request.getParameter("length");  
			int number = Integer.valueOf(length);
			List items = fd_warehouseservice.adminfindfd_warehouselist1(jqcode,bdcode,start,number);
			List count = fd_warehouseservice.adminfindfd_warehouselistcount1(jqcode,bdcode);
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
	@RequestMapping("/adminfd_warehousereset")
	public String adminfd_warehousereset(HttpServletRequest request, HttpServletResponse resp,
			Integer warehouseNum) 
			throws IOException {
		fd_warehouse dataprocess = fd_warehouseservice.get(warehouseNum);
		request.setAttribute("warehouseNum", warehouseNum);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/fd_warehousereset";
	}
	
	//信息添加
	@RequestMapping("/adminfd_warehouseadd")
	public String adminfd_warehouseadd(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_warehouseadd";
	}
	
	@RequestMapping(value = "/adminfd_warehouseresetajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_warehouseresetajax(HttpServletRequest request,Integer warehouseNum,String warehouseName,String warehouseFreight) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			fd_warehouse  dataprocess = fd_warehouseservice.get(warehouseNum);
	        dataprocess.setWarehouseNum(warehouseNum);
	        dataprocess.setWarehouseName(warehouseName);
	       
	        dataprocess.setWarehouseFreight(warehouseFreight);
	        
	        fd_warehouseservice.saveOrUpdate(dataprocess);
	        
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
		@RequestMapping(value = "/adminfd_warehousedeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private Map admindeleteajax1(HttpServletRequest request,Integer id) throws IOException {
			Map map = new HashMap<String, String>();
			try{
				if(id==1){
					map.put("code", "200");
				}else{
					fd_warehouse  dataprocess = fd_warehouseservice.get(id);
					fd_warehouseservice.deleteObject(dataprocess);
					map.put("code", "100");
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("code", "400");
			}
			return map;
		}
	//增加新
	@RequestMapping(value = "/adminfd_warehouseaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_warehouseaddajax(HttpServletRequest request,int warehouseNum,String warehouseName,String warehouseFreight) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			List count = fd_warehouseservice.adminfindaccount(warehouseNum,warehouseName,warehouseFreight);
			Map map0 = (Map) count.get(0);
			int countnumber = 0;
			countnumber = Integer.valueOf(String.valueOf(map0.get("count")));
			
			System.out.println(countnumber);
			if(countnumber!= 0){
				map.put("code", "300");
				map.put("info", "已存在");
			}else{
				fd_warehouse  dataprocess = new fd_warehouse();
				dataprocess.setWarehouseNum(warehouseNum);
		        dataprocess.setWarehouseName(warehouseName);
		      
		        dataprocess.setWarehouseFreight(warehouseFreight);
		        
		        fd_warehouseservice.saveOrUpdate(dataprocess);
		        
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
	@RequestMapping(value = "/adminfd_warehouselistExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_warehouselistExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("仓库编号");
			header.add("仓库名称");
			header.add("货位");
		    
		    
		    List items = fd_warehouseservice.adminfindfd_warehouselist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("warehouseNum")));
				data.add(String.valueOf(map.get("warehouseName")));
				data.add(String.valueOf(map.get("warehouseFreight")));
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






