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
import com.hd.microblog.model.fd_supplier;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.fd_supplierService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class Adminfd_supplierController {
	 
	@Autowired
	@Qualifier("fd_supplierService")
	private fd_supplierService fd_supplierservice;
	
	//预测查询表
	@RequestMapping("/adminfd_supplierlist")
	public String adminfd_supplierlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_supplierlist";
	}
	
  //列表
		@RequestMapping(value = "/adminfd_supplierlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminfd_supplierlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw 
			) throws IOException {
			
			Enumeration enu=request.getParameterNames();  
			while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			
			}
			Integer start = Integer.valueOf(request.getParameter("start"));  
		    String length = request.getParameter("length");  
		    String supName = request.getParameter("supName");  
		    int number = Integer.valueOf(length);
			List items = fd_supplierservice.adminfindfd_supplierlist(start,number);
			List count = fd_supplierservice.adminfindfd_supplierlistcount(supName);
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
		@RequestMapping(value = "/adminfd_supplierlist1ajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminmachinelistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
				String jqcode,String bdcode) throws IOException {
			
			Integer start = Integer.valueOf(request.getParameter("start"));
		    String length = request.getParameter("length");  
			int number = Integer.valueOf(length);
			List items = fd_supplierservice.adminfindfd_supplierlist1(jqcode,bdcode,start,number);
			List count = fd_supplierservice.adminfindfd_supplierlistcount1(jqcode,bdcode);
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
	@RequestMapping("/adminfd_supplierreset")
	public String adminfd_supplierreset(HttpServletRequest request, HttpServletResponse resp,
			Integer supNum) 
			throws IOException {
		fd_supplier dataprocess = fd_supplierservice.get(supNum);
		request.setAttribute("supNum", supNum);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/fd_supplierreset";
	}
	
	//信息添加
	@RequestMapping("/adminfd_supplieradd")
	public String adminfd_supplieradd(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_supplieradd";
	}
	
	@RequestMapping(value = "/adminfd_supplierresetajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_supplierresetajax(HttpServletRequest request,Integer supNum,String supName) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			fd_supplier  dataprocess = fd_supplierservice.get(supNum);
	        dataprocess.setsupNum(supNum);
	        dataprocess.setsupName(supName);
	        fd_supplierservice.saveOrUpdate(dataprocess);
	        
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
		@RequestMapping(value = "/adminfd_supplierdeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private Map admindeleteajax1(HttpServletRequest request,Integer id) throws IOException {
			Map map = new HashMap<String, String>();
			try{
				if(id==1){
					map.put("code", "200");
				}else{
					fd_supplier  dataprocess = fd_supplierservice.get(id);
					fd_supplierservice.deleteObject(dataprocess);
					map.put("code", "100");
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("code", "400");
			}
			return map;
		}
	//增加新
	@RequestMapping(value = "/adminfd_supplieraddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_supplieraddajax(HttpServletRequest request,int supNum,String supName,String phoneNum,String perWork) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			List count = fd_supplierservice.adminfindaccount(supNum,supName);
			Map map0 = (Map) count.get(0);
			int countnumber = 0;
			countnumber = Integer.valueOf(String.valueOf(map0.get("count")));
			
			System.out.println(countnumber);
			if(countnumber!= 0){
				map.put("code", "300");
				map.put("info", "已存在");
			}else{
				fd_supplier  dataprocess = new fd_supplier();
				dataprocess.setsupNum(supNum);
		        dataprocess.setsupName(supName);
		        
		        fd_supplierservice.saveOrUpdate(dataprocess);
		        
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
	@RequestMapping(value = "/adminfd_supplierlistExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_supplierlistExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("入库类别编号");
			header.add("入库类别名称");
		 
		    
		    
		    List items = fd_supplierservice.adminfindfd_supplierlist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("supNum")));
				data.add(String.valueOf(map.get("supName")));
			
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






