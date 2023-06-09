package com.hd.microblog.web.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.common.util.MD5;
import com.hd.microblog.model.dt_admin;
import com.hd.microblog.service.dt_adminService;





@Controller
public class AdminadminController {
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	
	//管理员信息列表
	@RequestMapping("/adminlist")
	public String adminlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/adminlist";
	}
	//管理员信息列表
	@RequestMapping(value = "/adminlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw 
			) throws IOException {
		
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		String paraName=(String)enu.nextElement();  
		System.out.println(paraName+": "+request.getParameter(paraName));  
		}
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
	    int number = Integer.valueOf(length);
		/*HttpSession session = request.getSession();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);*/
		List items = dt_adminservice.adminfindadminlist(start,number);
		List count = dt_adminservice.adminfindadminlistcount();
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			if(Integer.valueOf(String.valueOf(map.get("level")))==1){
				map.put("levelname", "超级管理员");
			}else if(Integer.valueOf(String.valueOf(map.get("level")))==2){
				map.put("levelname", "管理员");
			}else if(Integer.valueOf(String.valueOf(map.get("level")))==3){
				map.put("levelname", "操作者");
			}
			returnlist.add(map);
		}
		
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		
		System.out.println(jobj.toString());
		return jobj.toString();
	}
	//密码修改
	@RequestMapping("/adminpassword")
	public String landlordpassword(HttpServletRequest request, HttpServletResponse resp,
			Integer admin_id) 
			throws IOException {
		request.setAttribute("admin_id", admin_id);
		return "admin/adminpassword";
	}
	//密码修改
	@RequestMapping(value = "/adminpasswordajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminpasswordajax(HttpServletRequest request,String oldpassword,
			String newpassword,String newpassword2,Integer admin_id
			) throws IOException {
		Map map = new HashMap<String, String>();
		/*HttpSession session = request.getSession();
		dt_admin admin = (dt_admin) session.getAttribute("admin");*/
		try{
			dt_admin admin = dt_adminservice.get(admin_id);
			String oldpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(oldpassword, "UTF-8"), "UTF-8");
			String newpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(newpassword, "UTF-8"), "UTF-8");
			if(admin.getPassword().equals(oldpasswordmd5)){
				admin.setPassword(newpasswordmd5);
				dt_adminservice.saveOrUpdate(admin);
				map.put("code", "100");
				map.put("info", "修改成功");
			}else{
				map.put("code", "400");
				map.put("info", "原密码错误");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	//添加管理员
	@RequestMapping("/adminadd")
	public String adminadd(HttpServletRequest request, HttpServletResponse resp,
			Integer admin_id) 
			throws IOException {
		request.setAttribute("admin_id", admin_id);
		return "admin/adminadd";
	}
	//添加管理员
	@RequestMapping(value = "/adminaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminaddajax(HttpServletRequest request,String account,
			String newpassword,String newpassword2,Integer level) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			List adminlist = dt_adminservice.adminfindaccount(account);
			if(adminlist.size()>0){
				map.put("code", "300");
				map.put("info", "账号已存在");
			}else{
				String newpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(newpassword, "UTF-8"), "UTF-8");
				dt_admin admin = new dt_admin();
				admin.setAccount(account);
				admin.setPassword(newpasswordmd5);
				admin.setLevel(level);
				admin.setStatus(1);
				admin.setCreatetime(Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)));
				dt_adminservice.saveOrUpdate(admin);
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
	//编辑管理员权限
	@RequestMapping("/adminedit")
	public String adminedit(HttpServletRequest request, HttpServletResponse resp,
			Integer admin_id) 
			throws IOException {
		request.setAttribute("admin_id", admin_id);
		dt_admin admin = dt_adminservice.get(admin_id);
		request.setAttribute("level", admin.getLevel());
		return "admin/adminedit";
	}
	//编辑管理员权限
	@RequestMapping(value = "/admineditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map admineditajax(HttpServletRequest request,Integer admin_id,
			Integer level) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			dt_admin admin = dt_adminservice.get(admin_id);
			admin.setLevel(level);
			dt_adminservice.saveOrUpdate(admin);
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
	@RequestMapping(value = "/admindeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map admindeleteajax(HttpServletRequest request,Integer id) throws IOException {
		System.out.println("shanchu");
		Map map = new HashMap<String, String>();
		System.out.println(id);
		try{
			if(id==1){
				map.put("code", "200");
			}else{
				dt_admin admin = dt_adminservice.get(id);
				admin.setStatus(2);
				dt_adminservice.saveOrUpdate(admin);
				map.put("code", "100");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
		}
		return map;
	}
	//重置密码（111111）
	@RequestMapping(value = "/adminresetpasswordajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminresetpasswordajax(HttpServletRequest request,Integer id) throws IOException {
		Map map = new HashMap<String, String>();
		try{
			dt_admin admin  = dt_adminservice.get(id);
			admin.setPassword("699950c26dd523e44080dda7d4001827");
			dt_adminservice.saveOrUpdate(admin);
			map.put("code", "100");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
		}
		return map;
	}
}






