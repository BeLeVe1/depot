package com.hd.microblog.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.hd.microblog.model.dt_analysis;
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.dt_prediction;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_analysisService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.dt_datarecordService;
import com.hd.microblog.service.dt_predictionService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class AdminsharedpartController {
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_sharedpartService")
	private dt_sharedpartService dt_sharedpartservice;
	@Autowired
	@Qualifier("dt_analysisService")
	private dt_analysisService dt_analysisservice;
	@Autowired
	@Qualifier("dt_datarecordService")
	private dt_datarecordService dt_datarecordservice;
	@Autowired
	@Qualifier("dt_dataprocessService")
	private dt_dataprocessService dt_dataprocessservice;
	@Autowired
	@Qualifier("dt_predictionService")
	private dt_predictionService dt_predictionservice;
	
	//部队
	@RequestMapping("/adminsharedpartbdlist")
	public String adminsharedpartbdlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/sharedpartbdlist";
	}
	//战区
	@RequestMapping("/adminsharedpartjqlist")
	public String adminsharedpartjqlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/sharedpartjqlist";
	}
	//陆装
	@RequestMapping("/adminsharedpartzblist")
	public String adminsharedpartzblist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/sharedpartzblist";
	}
	//共用件优化表
	@RequestMapping(value = "/adminsharedpartlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminsharedpartlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg,String sort) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));
	    String length = request.getParameter("length");
		int number = Integer.valueOf(length);
		List items = dt_sharedpartservice.adminfindsharedpartlist(jqcode,bdcode,zbcode,qccode,qcname,fg,sort,start,number);
		List count = dt_sharedpartservice.adminfindsharedpartlistcount(jqcode,bdcode,zbcode,qccode,qcname,fg);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		String jqcode2="";
		String qccode2="";
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			if(fg.equals("陆装")){
				List list = dt_sharedpartservice.adminfindsharedpartlistsum(Integer.valueOf(String.valueOf(map.get("qccode"))));
				Map map2 = (Map)list.get(0);
				map.put("plannumber", map2.get("plannumber"));
				if(String.valueOf(map.get("qccode")).equals(qccode2)) {
					map.put("plannumber", "——");
					map.put("kyd", "——");
					map.put("czflag", 0);
					qccode2=String.valueOf(map.get("qccode"));
				}else {
					map.put("czflag", 1);
					qccode2=String.valueOf(map.get("qccode"));
				}
				returnlist.add(map);
			}else if(fg.equals("部队")) {
				map.put("czflag", 1);
				returnlist.add(map);
			}else {
				List list = dt_sharedpartservice.adminfindsharedpartlistsum(Integer.valueOf(String.valueOf(map.get("jqcode"))),Integer.valueOf(String.valueOf(map.get("qccode"))));
				Map map2 = (Map)list.get(0);
				map.put("plannumber", map2.get("plannumber"));
				if(String.valueOf(map.get("jqcode")).equals(jqcode2)&&String.valueOf(map.get("qccode")).equals(qccode2)) {
					map.put("plannumber", "——");
					map.put("kyd", "——");
					map.put("czflag", 0);
					jqcode2=String.valueOf(map.get("jqcode"));
					qccode2=String.valueOf(map.get("qccode"));
				}else {
					map.put("czflag", 1);
					jqcode2=String.valueOf(map.get("jqcode"));
					qccode2=String.valueOf(map.get("qccode"));
				}
				returnlist.add(map);
			}
		}
		
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}
	//Excel生成--陆装
	@RequestMapping(value = "/adminsharedpartzbExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminsharedpartzbExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			String fg="陆装";
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("战区编号");
			header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("单位");
		    header.add("单价");
		    header.add("单装用数");
		    header.add("分工");
		    header.add("预测所需数");
		    header.add("最大消耗数");
		    header.add("满足率");
		    header.add("计划优化数");
		    String jqcode2="";
			String qccode2="";
		    List items = dt_sharedpartservice.adminfindsharedpartlist(fg);
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("jqcode")));
				data.add(String.valueOf(map.get("bdcode")));
				data.add(String.valueOf(map.get("zbcode")));
				data.add(String.valueOf(map.get("qccode")));
				data.add(String.valueOf(map.get("qcname")));
				data.add(String.valueOf(map.get("unit")));
				data.add(String.valueOf(map.get("unitprice")));
				data.add(String.valueOf(map.get("dzys")));
				data.add(String.valueOf(map.get("fg")));
				data.add(String.valueOf(map.get("predictionnumber")));
				data.add(String.valueOf(map.get("maxnumber")));
				
				List list = dt_sharedpartservice.adminfindsharedpartlistsum(Integer.valueOf(String.valueOf(map.get("qccode"))));
				Map map2 = (Map)list.get(0);
				map.put("plannumber", map2.get("plannumber"));
				if(String.valueOf(map.get("qccode")).equals(qccode2)) {
					map.put("plannumber", "——");
					map.put("kyd", "——");
					qccode2=String.valueOf(map.get("qccode"));
				}else {
					qccode2=String.valueOf(map.get("qccode"));
				}
				
				data.add(String.valueOf(map.get("kyd")));
				data.add(String.valueOf(map.get("plannumber")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"共用件优化-陆装级.xls")){
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
	//Excel生成--部队
	@RequestMapping(value = "/adminsharedpartbdExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminsharedpartbdExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			String fg="部队";
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("战区编号");
			header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("单位");
		    header.add("单价");
		    header.add("单装用数");
		    header.add("分工");
		    header.add("预测所需数");
		    header.add("最大消耗数");
		    header.add("满足率");
		    header.add("计划优化数");
		    String jqcode2="";
			String qccode2="";
		    List items = dt_sharedpartservice.adminfindsharedpartlist(fg);
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("jqcode")));
				data.add(String.valueOf(map.get("bdcode")));
				data.add(String.valueOf(map.get("zbcode")));
				data.add(String.valueOf(map.get("qccode")));
				data.add(String.valueOf(map.get("qcname")));
				data.add(String.valueOf(map.get("unit")));
				data.add(String.valueOf(map.get("unitprice")));
				data.add(String.valueOf(map.get("dzys")));
				data.add(String.valueOf(map.get("fg")));
				data.add(String.valueOf(map.get("predictionnumber")));
				data.add(String.valueOf(map.get("maxnumber")));
				
//				List list = dt_sharedpartservice.adminfindsharedpartlistsum(Integer.valueOf(String.valueOf(map.get("jqcode"))),Integer.valueOf(String.valueOf(map.get("qccode"))));
//				Map map2 = (Map)list.get(0);
//				map.put("plannumber", map2.get("plannumber"));
//				if(String.valueOf(map.get("jqcode")).equals(jqcode2)&&String.valueOf(map.get("qccode")).equals(qccode2)) {
//					map.put("plannumber", "——");
//					map.put("kyd", "——");
//					jqcode2=String.valueOf(map.get("jqcode"));
//					qccode2=String.valueOf(map.get("qccode"));
//				}else {
//					jqcode2=String.valueOf(map.get("jqcode"));
//					qccode2=String.valueOf(map.get("qccode"));
//				}
				
				data.add(String.valueOf(map.get("kyd")));
				data.add(String.valueOf(map.get("plannumber")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"共用件优化-部队级.xls")){
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
	//Excel生成--战区
	@RequestMapping(value = "/adminsharedpartjqExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminsharedpartjqExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			String fg="战区";
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("战区编号");
			header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("单位");
		    header.add("单价");
		    header.add("单装用数");
		    header.add("分工");
		    header.add("预测所需数");
		    header.add("最大消耗数");
		    header.add("满足率");
		    header.add("计划优化数");
		    String jqcode2="";
			String qccode2="";
		    List items = dt_sharedpartservice.adminfindsharedpartlist(fg);
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("jqcode")));
				data.add(String.valueOf(map.get("bdcode")));
				data.add(String.valueOf(map.get("zbcode")));
				data.add(String.valueOf(map.get("qccode")));
				data.add(String.valueOf(map.get("qcname")));
				data.add(String.valueOf(map.get("unit")));
				data.add(String.valueOf(map.get("unitprice")));
				data.add(String.valueOf(map.get("dzys")));
				data.add(String.valueOf(map.get("fg")));
				data.add(String.valueOf(map.get("predictionnumber")));
				data.add(String.valueOf(map.get("maxnumber")));
				
				List list = dt_sharedpartservice.adminfindsharedpartlistsum(Integer.valueOf(String.valueOf(map.get("jqcode"))),Integer.valueOf(String.valueOf(map.get("qccode"))));
				Map map2 = (Map)list.get(0);
				map.put("plannumber", map2.get("plannumber"));
				if(String.valueOf(map.get("jqcode")).equals(jqcode2)&&String.valueOf(map.get("qccode")).equals(qccode2)) {
					map.put("plannumber", "——");
					map.put("kyd", "——");
					jqcode2=String.valueOf(map.get("jqcode"));
					qccode2=String.valueOf(map.get("qccode"));
				}else {
					jqcode2=String.valueOf(map.get("jqcode"));
					qccode2=String.valueOf(map.get("qccode"));
				}
				
				data.add(String.valueOf(map.get("kyd")));
				data.add(String.valueOf(map.get("plannumber")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"共用件优化-战区级.xls")){
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
	//装备修改--单条可用度
	@RequestMapping("/adminsharedpartedit")
	public String adminsharedpartedit(HttpServletRequest request, HttpServletResponse resp,Integer sharedpart_id) 
			throws IOException {
		dt_sharedpart sharedpart = dt_sharedpartservice.get(sharedpart_id);
		request.setAttribute("sharedpart", sharedpart);
		return "admin/sharedpartedit";
	}	
	@RequestMapping(value = "/adminsharedparteditajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminsharedparteditajax(HttpServletRequest request,HttpServletResponse resp,Integer sharedpart_id,
			Double kyd) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		try{
			dt_sharedpart sharedpart = dt_sharedpartservice.get(sharedpart_id);
			sharedpart.setKyd(kyd);
			
			List predictionlist = dt_predictionservice.adminfindpredictionlistfordpid(sharedpart.getDataprocess_id());
			Map map = (Map)predictionlist.get(0);
			double predictionnumber = Double.valueOf(String.valueOf(map.get("ycz11")));
//			double bzc = Double.valueOf(String.valueOf(map.get("bzc11")));
//			double fx = Double.valueOf(String.valueOf(map.get("fx11")));
			System.out.println("======================"+Double.valueOf(String.valueOf(map.get("zsz1"))));
			double[] data = {Double.valueOf(String.valueOf(map.get("zsz1"))),
					Double.valueOf(String.valueOf(map.get("zsz2"))),
					Double.valueOf(String.valueOf(map.get("zsz3"))),
					Double.valueOf(String.valueOf(map.get("zsz4"))),
					Double.valueOf(String.valueOf(map.get("zsz5"))),
					Double.valueOf(String.valueOf(map.get("zsz6"))),
					Double.valueOf(String.valueOf(map.get("zsz7"))),
					Double.valueOf(String.valueOf(map.get("zsz8"))),
					Double.valueOf(String.valueOf(map.get("zsz9"))),
					Double.valueOf(String.valueOf(map.get("zsz10"))),
					Double.valueOf(String.valueOf(map.get("ycz11")))};
			
			sharedpart.setPredictionnumber(Integer.valueOf(df.format(predictionnumber)));
//			sharedpart.setMaxnumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9)));
//			sharedpart.setPlannumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9*(kyd*2-1))));
			sharedpart.setMaxnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,1)))));
			sharedpart.setPlannumber(Integer.valueOf(String.valueOf(df.format(percentile(data,kyd)))));
			
			dt_sharedpartservice.saveOrUpdate(sharedpart);
			json.put("code", "100");
			json.put("info", "更新成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}
	//data年份1到年份10的消耗数据     p满足率     返回计划优化的值
	public static double percentile(double[] data,double p){  
	    int n = data.length;  
	    Arrays.sort(data);  
	    double px =  p*(n-1);  
	    int i = (int)java.lang.Math.floor(px);  
	    double g = px - i;  
	    if(g==0){  
	        return data[i];  
	    }else{  
	        return (1-g)*data[i]+g*data[i+1];  
	    }  
	}	
	//装备可用度修改--战区
	@RequestMapping("/adminkydjqedit")
	public String adminkydjqedit(HttpServletRequest request, HttpServletResponse resp,Integer sharedpart_id) 
			throws IOException {
		String fg="战区";
		List list = dt_sharedpartservice.adminfindsharedpartlistforfg(fg);
		if(list.size()>0) {
			Map map = (Map)list.get(0);
			request.setAttribute("kyd", map.get("kyd"));
		}else {
			request.setAttribute("kyd", 0);
		}
		return "admin/kydjqedit";
	}
	//装备可用度修改--部队
	@RequestMapping("/adminkydbdedit")
	public String adminkydbdedit(HttpServletRequest request, HttpServletResponse resp,Integer sharedpart_id) 
			throws IOException {
		String fg="部队";
		List list = dt_sharedpartservice.adminfindsharedpartlistforfg(fg);
		if(list.size()>0) {
			Map map = (Map)list.get(0);
			request.setAttribute("kyd", map.get("kyd"));
		}else {
			request.setAttribute("kyd", 0);
		}
		return "admin/kydbdedit";
	}
	//装备可用度修改--陆装
	@RequestMapping("/adminkydzbedit")
	public String adminkydzbedit(HttpServletRequest request, HttpServletResponse resp,Integer sharedpart_id) 
			throws IOException {
		String fg="陆装";
		List list = dt_sharedpartservice.adminfindsharedpartlistforfg(fg);
		if(list.size()>0) {
			Map map = (Map)list.get(0);
			request.setAttribute("kyd", map.get("kyd"));
		}else {
			request.setAttribute("kyd", 0);
		}
		return "admin/kydzbedit";
	}
	@RequestMapping(value = "/adminkydeditajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminkydeditajax(HttpServletRequest request,HttpServletResponse resp,Double kyd,String fg) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		try{
			List list = dt_sharedpartservice.adminfindsharedpartlistforfg(fg);
			for(int i=0;i<list.size();i++){
				Map map = (Map)list.get(i);
				dt_sharedpart sharedpart = dt_sharedpartservice.get(Integer.valueOf(String.valueOf(map.get("sharedpart_id"))));
				sharedpart.setKyd(kyd);
				
				double predictionnumber = Double.valueOf(String.valueOf(map.get("ycz11")));
//				double bzc = Double.valueOf(String.valueOf(map.get("bzc11")));
//				double fx = Double.valueOf(String.valueOf(map.get("fx11")));
				
				double[] data = {Double.valueOf(String.valueOf(map.get("zsz1"))),
						Double.valueOf(String.valueOf(map.get("zsz2"))),
						Double.valueOf(String.valueOf(map.get("zsz3"))),
						Double.valueOf(String.valueOf(map.get("zsz4"))),
						Double.valueOf(String.valueOf(map.get("zsz5"))),
						Double.valueOf(String.valueOf(map.get("zsz6"))),
						Double.valueOf(String.valueOf(map.get("zsz7"))),
						Double.valueOf(String.valueOf(map.get("zsz8"))),
						Double.valueOf(String.valueOf(map.get("zsz9"))),
						Double.valueOf(String.valueOf(map.get("zsz10"))),
						Double.valueOf(String.valueOf(map.get("ycz11")))};
				
				sharedpart.setPredictionnumber(Integer.valueOf(df.format(predictionnumber)));
//				sharedpart.setMaxnumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9)));
//				sharedpart.setPlannumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9*(kyd*2-1))));
				sharedpart.setMaxnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,1)))));
				sharedpart.setPlannumber(Integer.valueOf(String.valueOf(df.format(percentile(data,kyd)))));
				
				dt_sharedpartservice.saveOrUpdate(sharedpart);
				System.out.println("共用件优化："+i);
			}
			json.put("code", "100");
			json.put("info", "更新成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}	
}






