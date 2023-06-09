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
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.dt_datarecord;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.dt_datarecordService;
import com.hd.microblog.util.createxls;




@Controller
public class AdmindataprocessController {
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_dataprocessService")
	private dt_dataprocessService dt_dataprocessservice;
	@Autowired
	@Qualifier("dt_datarecordService")
	private dt_datarecordService dt_datarecordservice;
	
	//数据处理表
	@RequestMapping("/admindataprocesslist")
	public String admindataprocesslist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/dataprocesslist";
	}
	//数据处理表
	@RequestMapping(value = "/admindataprocesslistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String admindataprocesslistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg,String sort) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_dataprocessservice.adminfinddataprocesslist(jqcode,bdcode,zbcode,qccode,qcname,fg,sort,start,number);
		List count = dt_dataprocessservice.adminfinddataprocesslistcount(jqcode,bdcode,zbcode,qccode,qcname,fg);
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
	//数据处理使用记录添加
	@RequestMapping("/admindatarecordadd")
	public String admindatarecordadd(HttpServletRequest request, HttpServletResponse resp,
			Integer dataprocess_id) 
			throws IOException {
		dt_dataprocess  dataprocess = dt_dataprocessservice.get(dataprocess_id);
		request.setAttribute("dataprocess_id", dataprocess_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/datarecordadd";
	}
	@RequestMapping(value = "/admindatarecordaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map admindatarecordaddajax(HttpServletRequest request,Integer dataprocess_id,Integer time,
			String text,Integer number) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{
//			//获取登录用户信息
//			dt_admin admin = (dt_admin) session.getAttribute("admin");
//			//时间转换成时间戳
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date = sdf.parse(time);
//			long recordtime = date.getTime()/1000;
//			//保存记录
//			dt_datarecord datarecord = new dt_datarecord();
//			datarecord.setDataprocess_id(dataprocess_id);
//			datarecord.setText(text);
//			datarecord.setNumber(number);
//			datarecord.setRecordtime(Integer.valueOf(String.valueOf(recordtime)));
//			datarecord.setAdmin_id(1);
//			datarecord.setCreatetime(Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)));
//			datarecord.setFlag(0);
//			dt_datarecordservice.saveOrUpdate(datarecord);
//			
//			//获取当年日期
//			Calendar calendar = Calendar.getInstance();
//	        calendar.set(Calendar.MONTH,0);
//	        calendar.set(Calendar.DAY_OF_MONTH,1);
//	        calendar.set(Calendar.HOUR_OF_DAY,0);
//	        calendar.set(Calendar.MINUTE,0);
//	        calendar.set(Calendar.SECOND,0);
//	        calendar.set(Calendar.MILLISECOND,0);
//	        Long time1 = calendar.getTime().getTime()/1000;//年份10
//	        
//	        calendar.add(Calendar.MONTH,12);
//	        Long time0 = calendar.getTime().getTime()/1000;//年份11
//	        
//	        calendar.add(Calendar.MONTH,-24);
//	        Long time2 = calendar.getTime().getTime()/1000;//年份9
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time3 = calendar.getTime().getTime()/1000;//年份8
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time4 = calendar.getTime().getTime()/1000;//年份7
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time5 = calendar.getTime().getTime()/1000;//年份6
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time6 = calendar.getTime().getTime()/1000;//年份5
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time7 = calendar.getTime().getTime()/1000;//年份4
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time8 = calendar.getTime().getTime()/1000;//年份3
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time9 = calendar.getTime().getTime()/1000;//年份2
//	        
//	        calendar.add(Calendar.MONTH,-12);
//	        Long time10 = calendar.getTime().getTime()/1000;//年份1
	        
	        dt_dataprocess  dataprocess = dt_dataprocessservice.get(dataprocess_id);
	        if(time==1) {
	        	dataprocess.setAge1(number);
	        }else if(time==2) {
	        	dataprocess.setAge2(number);
	        }else if(time==3) {
	        	dataprocess.setAge3(number);
	        }else if(time==4) {
	        	dataprocess.setAge4(number);
	        }else if(time==5) {
	        	dataprocess.setAge5(number);
	        }else if(time==6) {
	        	dataprocess.setAge6(number);
	        }else if(time==7) {
	        	dataprocess.setAge7(number);
	        }else if(time==8) {
	        	dataprocess.setAge8(number);
	        }else if(time==9) {
	        	dataprocess.setAge9(number);
	        }else if(time==10) {
	        	dataprocess.setAge10(number);
	        }
	        dt_dataprocessservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "添加失败");
		}
		return map;
	}
//	//数据添加记录
//	@RequestMapping("/admindatarecordlist")
//	public String admindatarecordlist(HttpServletRequest request, HttpServletResponse resp,
//			Integer type,Integer dataprocess_id,Integer flag) 
//			throws IOException {
//		request.setAttribute("flag", flag);
//		request.setAttribute("type", type);
//		request.setAttribute("dataprocess_id", dataprocess_id);
//		return "admin/datarecordlist";
//	}
//	@RequestMapping(value = "/admindatarecordlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
//	@ResponseBody
//	private String admindatarecordlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
//			Integer type,Integer dataprocess_id,Integer flag) throws IOException {
//		
//		//获取当年日期
//		Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH,0);
//        calendar.set(Calendar.DAY_OF_MONTH,1);
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//        Long time1 = calendar.getTime().getTime()/1000;//年份10
//        
//        calendar.add(Calendar.MONTH,12);
//        Long time0 = calendar.getTime().getTime()/1000;//年份11
//        
//        calendar.add(Calendar.MONTH,-24);
//        Long time2 = calendar.getTime().getTime()/1000;//年份9
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time3 = calendar.getTime().getTime()/1000;//年份8
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time4 = calendar.getTime().getTime()/1000;//年份7
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time5 = calendar.getTime().getTime()/1000;//年份6
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time6 = calendar.getTime().getTime()/1000;//年份5
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time7 = calendar.getTime().getTime()/1000;//年份4
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time8 = calendar.getTime().getTime()/1000;//年份3
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time9 = calendar.getTime().getTime()/1000;//年份2
//        
//        calendar.add(Calendar.MONTH,-12);
//        Long time10 = calendar.getTime().getTime()/1000;//年份1
//		
//		Long starttime = (long) 0;
//		Long endtime = (long) 0;
//		if(type==0) {
//			starttime = (long) 0;
//			endtime = time0;
//		}else if(type==1) {
//			starttime = time10;
//			endtime = time9;
//		}else if(type==2) {
//			starttime = time9;
//			endtime = time8;
//		}else if(type==3) {
//			starttime = time8;
//			endtime = time7;
//		}else if(type==4) {
//			starttime = time7;
//			endtime = time6;
//		}else if(type==5) {
//			starttime = time6;
//			endtime = time5;
//		}else if(type==6) {
//			starttime = time5;
//			endtime = time4;
//		}else if(type==7) {
//			starttime = time4;
//			endtime = time3;
//		}else if(type==8) {
//			starttime = time3;
//			endtime = time2;
//		}else if(type==9) {
//			starttime = time2;
//			endtime = time1;
//		}else if(type==10) {
//			starttime = time1;
//			endtime = time0;
//		}
//		
//		Integer start = Integer.valueOf(request.getParameter("start"));  
//	    String length = request.getParameter("length");  
//		int number = Integer.valueOf(length);
//		List items = dt_datarecordservice.adminfinddatarecordlist(start,number,dataprocess_id,flag,starttime,endtime);
//		List count = dt_datarecordservice.adminfinddatarecordlistcount(dataprocess_id,flag,starttime,endtime);
//		int countnumber = 0;
//		if (count != null && count.size() != 0) {
//			Map map = (Map) count.get(0);
//			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
//		}
//		List returnlist  = new ArrayList();
//		for(int i=0;i<items.size();i++){
//			Map map = (Map)items.get(i);
//			//时间转换
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Long time = Long.valueOf(String.valueOf(map.get("recordtime")))*1000;
//			Date date = new Date(time);
//			map.put("recordtime",sdf.format(date));
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
	//Excel生成
	@RequestMapping(value = "/admindataprocessExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String admindataprocessExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("ID");
			header.add("军区编号");
		    header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("单位");
		    header.add("单价");
		    header.add("单装用数");
		    header.add("分工");
		    header.add("年份1");
		    header.add("年份2");
		    header.add("年份3");
		    header.add("年份4");
		    header.add("年份5");
		    header.add("年份6");
		    header.add("年份7");
		    header.add("年份8");
		    header.add("年份9");
		    header.add("年份10");
		    
		    List items = dt_dataprocessservice.adminfinddataprocessslist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("dataprocess_id")));
				data.add(String.valueOf(map.get("jqcode")));
				data.add(String.valueOf(map.get("bdcode")));
				data.add(String.valueOf(map.get("zbcode")));
				data.add(String.valueOf(map.get("qccode")));
				data.add(String.valueOf(map.get("qcname")));
				data.add(String.valueOf(map.get("unit")));
				data.add(String.valueOf(map.get("unitprice")));
				data.add(String.valueOf(map.get("dzys")));
				data.add(String.valueOf(map.get("fg")));
				data.add(String.valueOf(map.get("age1")));
				data.add(String.valueOf(map.get("age2")));
				data.add(String.valueOf(map.get("age3")));
				data.add(String.valueOf(map.get("age4")));
				data.add(String.valueOf(map.get("age5")));
				data.add(String.valueOf(map.get("age6")));
				data.add(String.valueOf(map.get("age7")));
				data.add(String.valueOf(map.get("age8")));
				data.add(String.valueOf(map.get("age9")));
				data.add(String.valueOf(map.get("age10")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"数据处理.xls")){
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
	//导入Excel表格
	@RequestMapping("/admindataprocessexcelimport")
	public String admindataprocessexcelimport(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/dataprocessexcelimport";
	}
	//导入
	@RequestMapping(value = "/admindataprocessexcelimportajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String admindataprocessexcelimportajax(HttpServletRequest request, HttpServletResponse resp,String filename) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		try {
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
			String xlsfile = loadpath+"/"+filename;
			File xlsFile = new File(xlsfile);     
			/*
			 * 1.XSSFWorkbook 
			 * 2.HSSFWorkbook
			 */
	        // 获得工作簿
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsFile));

	        // 获得工作表
	        HSSFSheet sheet = workbook.getSheetAt(0);

	        int rows = sheet.getPhysicalNumberOfRows();

	        for (int i = 1; i < rows; i++) {
	            // 获取第i行数据
	            HSSFRow sheetRow = sheet.getRow(i);
	            HSSFCell id = sheetRow.getCell(0);
	            HSSFCell jqcode = sheetRow.getCell(1);
	            HSSFCell bdcode = sheetRow.getCell(2);
	            HSSFCell zbcode = sheetRow.getCell(3);
	            HSSFCell qccode = sheetRow.getCell(4);
	            HSSFCell qcname = sheetRow.getCell(5);
	            HSSFCell unit = sheetRow.getCell(6);
	            HSSFCell unitprice = sheetRow.getCell(7);
	            HSSFCell dzys = sheetRow.getCell(8);
	            HSSFCell fg = sheetRow.getCell(9);
	            HSSFCell age1 = sheetRow.getCell(10);
	            HSSFCell age2 = sheetRow.getCell(11);
	            HSSFCell age3 = sheetRow.getCell(12);
	            HSSFCell age4 = sheetRow.getCell(13);
	            HSSFCell age5 = sheetRow.getCell(14);
	            HSSFCell age6 = sheetRow.getCell(15);
	            HSSFCell age7 = sheetRow.getCell(16);
	            HSSFCell age8 = sheetRow.getCell(17);
	            HSSFCell age9 = sheetRow.getCell(18);
	            HSSFCell age10 = sheetRow.getCell(19);
	            
	            dt_dataprocess dataprocess = dt_dataprocessservice.get(Integer.valueOf(String.valueOf(id)));
//	            dt_dataprocess dataprocess = new dt_dataprocess();
	            dataprocess.setJqcode(String.valueOf(jqcode));
	            dataprocess.setBdcode(String.valueOf(bdcode));
	            dataprocess.setZbcode(String.valueOf(zbcode));
	            dataprocess.setQccode(String.valueOf(qccode));
	            dataprocess.setQcname(String.valueOf(qcname));
	            dataprocess.setUnit(String.valueOf(unit));
	            dataprocess.setUnitprice(Double.valueOf(String.valueOf(unitprice)));
	            dataprocess.setDzys(Integer.valueOf(String.valueOf(dzys)));
	            dataprocess.setFg(String.valueOf(fg));
	            dataprocess.setAge1(Double.valueOf(String.valueOf(age1)));
	            dataprocess.setAge2(Double.valueOf(String.valueOf(age2)));
	            dataprocess.setAge3(Double.valueOf(String.valueOf(age3)));
	            dataprocess.setAge4(Double.valueOf(String.valueOf(age4)));
	            dataprocess.setAge5(Double.valueOf(String.valueOf(age5)));
	            dataprocess.setAge6(Double.valueOf(String.valueOf(age6)));
	            dataprocess.setAge7(Double.valueOf(String.valueOf(age7)));
	            dataprocess.setAge8(Double.valueOf(String.valueOf(age8)));
	            dataprocess.setAge9(Double.valueOf(String.valueOf(age9)));
	            dataprocess.setAge10(Double.valueOf(String.valueOf(age10)));
	            dt_dataprocessservice.saveOrUpdate(dataprocess);
	        }
			json.put("code", "100");
			json.put("rinfo", "导入成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("code", "400");
			json.put("rinfo", "系统错误");
		}
		return json.toString();
	}
	//测试
	@RequestMapping(value = "/test11", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String test11(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try {
			
			json.put("code", "100");
			json.put("rinfo", "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("code", "400");
			json.put("rinfo", "系统错误");
		}
		return json.toString();
	}
}


