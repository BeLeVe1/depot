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
import com.hd.microblog.model.fd_windfarm;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.model.fd_department;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.fd_windfarmService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class Adminfd_windfarmController {
	 
	@Autowired
	@Qualifier("fd_windfarmService")
	private fd_windfarmService fd_windfarmservice;
	
	//预测查询表
	@RequestMapping("/adminfd_windfarmlist")
	public String adminfd_windfarmlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_windfarmlist";
	}
	
  //列表
		@RequestMapping(value = "/adminfd_windfarmlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminfd_windfarmlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw 
			) throws IOException {
			
			Enumeration enu=request.getParameterNames();  
			while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			
			}
			Integer start = Integer.valueOf(request.getParameter("start"));  
		    String length = request.getParameter("length");  
		    String windfarmName = request.getParameter("windfarmName");  
		    int number = Integer.valueOf(length);
			List items = fd_windfarmservice.adminfindfd_windfarmlist(start,number);
			List count = fd_windfarmservice.adminfindfd_windfarmlistcount(windfarmName);
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
		@RequestMapping(value = "/adminfd_windfarmlist1ajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminmachinelistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
				String jqcode,String bdcode) throws IOException {
			
			Integer start = Integer.valueOf(request.getParameter("start"));
		    String length = request.getParameter("length");  
			int number = Integer.valueOf(length);
			List items = fd_windfarmservice.adminfindfd_windfarmlist1(jqcode,bdcode,start,number);
			List count = fd_windfarmservice.adminfindfd_windfarmlistcount1(jqcode,bdcode);
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
	@RequestMapping("/adminfd_windfarmreset")
	public String adminfd_windfarmreset(HttpServletRequest request, HttpServletResponse resp,
			Integer windfarmNum) 
			throws IOException {
		fd_windfarm dataprocess = fd_windfarmservice.get(windfarmNum);
		request.setAttribute("windfarmNum", windfarmNum);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/fd_windfarmreset";
	}
	
	//信息添加
	@RequestMapping("/adminfd_windfarmadd")
	public String adminfd_windfarmadd(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_windfarmadd";
	}
	
	@RequestMapping(value = "/adminfd_windfarmresetajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_windfarmresetajax(HttpServletRequest request,Integer windfarmNum,String windfarmName,String phoneNum,String windfarmRegion) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			fd_windfarm  dataprocess = fd_windfarmservice.get(windfarmNum);
	        dataprocess.setWindfarmNum(windfarmNum);
	        dataprocess.setWindfarmName(windfarmName);
//	        dataprocess.setPhoneNum(phoneNum);
	        dataprocess.setWindfarmRegion(windfarmRegion);
	        
	        fd_windfarmservice.saveOrUpdate(dataprocess);
	        
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
		@RequestMapping(value = "/adminfd_windfarmdeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
		@ResponseBody
		private Map admindeleteajax1(HttpServletRequest request,Integer id) throws IOException {
			Map map = new HashMap<String, String>();
			try{
				if(id==1){
					map.put("code", "200");
				}else{
					fd_windfarm  dataprocess = fd_windfarmservice.get(id);
					fd_windfarmservice.deleteObject(dataprocess);
					map.put("code", "100");
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("code", "400");
			}
			return map;
		}
		
	
	//增加新
	@RequestMapping(value = "/adminfd_windfarmaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_windfarmaddajax(HttpServletRequest request,int windfarmNum,String windfarmName,String windfarmRegion) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			List count = fd_windfarmservice.adminfindaccount(windfarmNum,windfarmName,windfarmRegion);
			Map map0 = (Map) count.get(0);
			int countnumber = 0;
			countnumber = Integer.valueOf(String.valueOf(map0.get("count")));
			
			System.out.println(countnumber);
			if(countnumber!= 0){
				map.put("code", "300");
				map.put("info", "已存在");
			}else{
				fd_windfarm  dataprocess = new fd_windfarm();
				dataprocess.setWindfarmNum(windfarmNum);
		        dataprocess.setWindfarmName(windfarmName);
//		        dataprocess.setPhoneNum(phoneNum);
		        dataprocess.setWindfarmRegion(windfarmRegion);
		        
		        fd_windfarmservice.saveOrUpdate(dataprocess);
		        
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
	@RequestMapping(value = "/adminfd_windfarmlistExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_windfarmlistExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
//			header.add("员工编号");
//			header.add("员工姓名");
//		    header.add("电话号码");
//			header.add("职务");
//		    
			header.add("风场编号");
			header.add("风场名称");
			header.add("所在区域");
		    
		    
		    List items = fd_windfarmservice.adminfindfd_windfarmlist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("windfarmNum")));
				data.add(String.valueOf(map.get("windfarmName")));
//				data.add(String.valueOf(map.get("phoneNum")));
				data.add(String.valueOf(map.get("windfarmRegion")));
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






