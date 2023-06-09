//package com.hd.microblog.web.controller.admin;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import net.sf.json.JSONObject;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.hd.microblog.model.dt_admin;
//import com.hd.microblog.model.dt_dataprocess;
//import com.hd.microblog.model.dt_datarecord;
//import com.hd.microblog.model.dt_plan;
//import com.hd.microblog.service.dt_adminService;
//import com.hd.microblog.service.dt_dataprocessService;
//import com.hd.microblog.service.dt_datarecordService;
//import com.hd.microblog.service.dt_planService;
//import com.hd.microblog.service.dt_sharedpartService;
//import com.hd.microblog.util.createxls;
//
//
//
//
//@Controller
//public class AdminkcController {
//	 
//	@Autowired
//	@Qualifier("dt_adminService")
//	private dt_adminService dt_adminservice;
//	@Autowired
//	@Qualifier("dt_planService")
//	private dt_planService dt_planservice;
//	@Autowired
//	@Qualifier("dt_datarecordService")
//	private dt_datarecordService dt_datarecordservice;
//	
//	//库存表
//	@RequestMapping("/adminkclist")
//	public String adminkclist(HttpServletRequest request, HttpServletResponse resp) 
//			throws IOException {
//		return "admin/kclist";
//	}
//	//库存表
//	@RequestMapping(value = "/adminkclistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
//	@ResponseBody
//	private String adminkclistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
//			String zbcode,String qccode,String qcname,String fg,String sort) throws IOException {
//		
//		Integer start = Integer.valueOf(request.getParameter("start"));  
//	    String length = request.getParameter("length");  
//		int number = Integer.valueOf(length);
//		List items = dt_planservice.adminfindplanlist(zbcode,qccode,qcname,fg,sort,start,number);
//		List count = dt_planservice.adminfindplanlistcount(zbcode,qccode,qcname,fg);
//		int countnumber = 0;
//		if (count != null && count.size() != 0) {
//			Map map = (Map) count.get(0);
//			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
//		}
//		List returnlist  = new ArrayList();
//		for(int i=0;i<items.size();i++){
//			Map map = (Map)items.get(i);
//			
//			returnlist.add(map);
//		}
//		
//		JSONObject jobj = new JSONObject();
//		
//		jobj.accumulate("draw", draw);
//		jobj.accumulate("recordsFiltered", countnumber);
//		jobj.accumulate("recordsTotal", countnumber);
//		jobj.accumulate("data", returnlist);
//		return jobj.toString();
//	}
//	//Excel生成
//	@RequestMapping(value = "/adminkcExcel",produces = "application/json; charset=utf-8")
//	@ResponseBody
//	private String adminkcExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
//		HttpSession session = request.getSession();
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
//		JSONObject json = new JSONObject();
//		try{
//			//生成xls表头
//			List<String> header = new ArrayList<String>(); // 第一行数据
//			List<List<String>> body = new ArrayList<List<String>>();
//		    header.add("器材代码");
//		    header.add("器材名称");
//		    header.add("规格件号");
//		    header.add("单位");
//		    header.add("单价");
//		    header.add("单装用数");
//		    header.add("分工");
//		    header.add("长度");
//		    header.add("宽度");
//		    header.add("高度");
//		    header.add("重量");
//		    header.add("仓库数量");
//		    header.add("剩余数");
//		    
//		    List<String> data1 = new ArrayList<String>();
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//			data1.add("");
//	    	body.add(data1);
//		    
//		    
//		    List items = dt_planservice.adminfindplanlist();
//			for(int i=0;i<items.size();i++){
//				Map map = (Map)items.get(i);
//				//添加xls信息
//				List<String> data = new ArrayList<String>();
//				data.add(String.valueOf(map.get("qccode")));
//				data.add(String.valueOf(map.get("qcname")));
//				data.add(String.valueOf(map.get("ggjh")));
//				data.add(String.valueOf(map.get("unit")));
//				data.add(String.valueOf(map.get("unitprice")));
//				data.add(String.valueOf(map.get("dzys")));
//				data.add(String.valueOf(map.get("fg")));
//				data.add(String.valueOf(map.get("length")));
//				data.add(String.valueOf(map.get("width")));
//				data.add(String.valueOf(map.get("height")));
//				data.add(String.valueOf(map.get("weight")));
//				data.add(String.valueOf(map.get("number")));
//				data.add(String.valueOf(map.get("lastnumber")));
//		    	body.add(data);
//		    	
//			}
//			//xls输出
//			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
//		    //新建文件路径
//		    File file2 = new File(loadpath);
//			if (!file2.exists()) {
//				file2.mkdir();
//			}
//			try(OutputStream out = new FileOutputStream(loadpath+"/"+"库存.xls")){
//				createxls.generateExcel("Sheet1", header, body, out);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			json.put("code", "100");
//			json.put("info", "生成成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			json.put("code", "400");
//			json.put("info", "系统错误");
//		}
//		return json.toString();
//	}	
//	//库存添加
//	@RequestMapping("/adminkcadd")
//	public String adminkcadd(HttpServletRequest request, HttpServletResponse resp,
//			Integer plan_id) 
//			throws IOException {
//		request.setAttribute("plan_id", plan_id);
//		return "admin/kcadd";
//	}
//	@RequestMapping(value = "/adminkcaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
//	@ResponseBody
//	private Map adminkcaddajax(HttpServletRequest request,Integer plan_id,String time,
//			String text,Integer number) throws IOException {
//		HttpSession session = request.getSession();
//		Map map = new HashMap<String, String>();
//		try{
//			//获取登录用户信息
//			dt_admin admin = (dt_admin) session.getAttribute("admin");
//			//时间转换成时间戳
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date = sdf.parse(time);
//			long recordtime = date.getTime()/1000;
//			//保存记录
//			dt_datarecord datarecord = new dt_datarecord();
//			datarecord.setDataprocess_id(plan_id);
//			datarecord.setText(text);
//			datarecord.setNumber(number);
//			datarecord.setRecordtime(Integer.valueOf(String.valueOf(recordtime)));
//			datarecord.setAdmin_id(1);
//			datarecord.setCreatetime(Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)));
//			datarecord.setFlag(2);
//			dt_datarecordservice.saveOrUpdate(datarecord);
//			
//			dt_plan plan = dt_planservice.get(plan_id);
//			plan.setNumber(plan.getNumber()+number);
//			plan.setLastnumber(plan.getLastnumber()+number);
//			dt_planservice.saveOrUpdate(plan);
//			
//			
//			map.put("code", "100");
//			map.put("info", "添加成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			map.put("code", "400");
//			map.put("info", "添加失败");
//		}
//		return map;
//	}	
//}
//
//
//
//
//
//
