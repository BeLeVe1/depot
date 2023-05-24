package com.hd.microblog.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.fd_preService;
import com.hd.microblog.util.CSVToMySQL;
import com.hd.microblog.util.MySQLToCSV;
import com.hd.microblog.util.Mysql2Tempsheet.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;

import static com.hd.microblog.util.Mysql2Tempsheet.sql2temp;

@Controller
public class Adminfd_preController {
	
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("fd_preService")
	private fd_preService fd_preService;
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/adminfd_pre")
	public String adminfd_pre(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_pre";
	}
	@RequestMapping("/fd_preProcess")
	public String adminadd(HttpServletRequest request, HttpServletResponse resp,
						   Integer fd_preProcess)
			throws IOException {
		request.setAttribute("fd_preProcess", fd_preProcess);
		return "admin/fd_preProcess";
	}
	
	@RequestMapping(value = "/adminfd_preajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_preajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
			String sort,
			String part_name) throws IOException {
		Integer start = Integer.valueOf(request.getParameter("start"));
		String length = request.getParameter("length");
		int number = Integer.valueOf(length);
		List items = fd_preService.adminfindfd_prelist(sort,start,number,part_name);
		
		System.out.println("items:");
		System.out.println(items);
	
		List returnlist  = new ArrayList();
		
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			returnlist.add(map);
		}
		JSONObject jobj = new JSONObject();
				
		System.out.println(returnlist);
		jobj.accumulate("data", returnlist);
		
		System.out.println("json为=");
		System.out.println(jobj);
		return jobj.toString();
		
	}
	@RequestMapping(value="/adminfd_preAlgorithmtest", produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_preAlgorithmtest(HttpServletRequest request,HttpServletResponse resp,String partName) throws IOException {
		JSONObject json = new JSONObject();
		if(partName==""){
			System.out.println("未输入备件名");
			System.out.println(CSVToMySQL.save());
			json.put("code", "500");
			json.put("info", "未输入备件名");
			return json.toString();
		}

		System.out.println("adminfd_preAlgorithmtest");
		MySQLToCSV mySQLToCSV = new MySQLToCSV();
		System.out.println(partName);
//		mySQLToCSV.sql2csv(partName);
     	sql2temp("4010144",partName);


		String python_url="C:\\Users\\84593\\AppData\\Local\\Microsoft\\WindowsApps\\python.exe";

		String testFile = request.getServletContext().getRealPath("/")+"GA-svm-master\\mysql.py";
		System.out.println(testFile);
		String[] testurl = new String[] { "python", testFile};
		System.out.println(Arrays.toString(testurl));
        try {
            //测试链接
        	
            Process testpr = Runtime.getRuntime().exec(testurl);
            BufferedReader testin = new BufferedReader(new InputStreamReader(testpr.getInputStream(),"GB2312"));
            //指定编码解决中文乱码 BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream());
            String testline = null;
            System.out.println("java:开始运行算法");
            int re = testpr.waitFor();
            System.out.println(re);
            if (re == 2) {
            	System.out.println("调用脚本失败");
            	json.put("code", "400");
    			json.put("info", "系统错误");;
            } else {
            	System.out.println("调用脚本成功");
            	System.out.println(CSVToMySQL.save());
            	json.put("code", "100");
    			json.put("info", "生成成功");
    			
            	
            }
            testin.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("adminfd_preAlgorithmtest 运行失败");
			json.put("code", "400");
			json.put("info", "系统错误");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("adminfd_preAlgorithmtest 运行失败");
			json.put("code", "400");
			json.put("info", "系统错误");
        }
		return json.toString();
	}
}
	
	