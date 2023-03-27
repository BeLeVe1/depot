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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hd.microblog.model.dt_admin;
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.dt_orderlist;
import com.hd.microblog.model.dt_prediction;
import com.hd.microblog.model.dt_realschedule;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_orderlistService;
import com.hd.microblog.util.createxls;

@Controller
public class AdminorderlistController {
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_orderlistService")
	private dt_orderlistService dt_orderlistservice;

	//订单信息表
	@RequestMapping("/adminorderlist")
	public String adminorderlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/orderlist";
	}
	//订单信息表
	@RequestMapping(value = "/adminorderlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminorderlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String orcode,String cpname,String cpcode,String gjname,String gjcode,String orstat,String gjstat) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_orderlistservice.adminfindorderlist(orcode,cpname,cpcode,gjname,gjcode,orstat,gjstat,start, number);
		List count = dt_orderlistservice.adminfindorderlistcount(orcode,cpname,cpcode,gjname,gjcode,orstat,gjstat);
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

	//订单信息修改
	@RequestMapping("/adminorderlistedit")
	public String adminorderlistedit(HttpServletRequest request, HttpServletResponse resp,
			Integer orderdata_id) 
			throws IOException {
		dt_orderlist dataprocess = dt_orderlistservice.get(orderdata_id);
		request.setAttribute("orderdata_id", orderdata_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/orderlistedit";
	}
	
	@RequestMapping(value = "/adminorderlisteditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminorderlisteditajax(HttpServletRequest request,Integer orderdata_id,Integer gjnumb) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			dt_orderlist  dataprocess = dt_orderlistservice.get(orderdata_id);
	        dataprocess.setgjnumb(gjnumb);
	        dt_orderlistservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	
	//投料计划制定，显示未加工的工件
	@RequestMapping("/admintouliaolist")
	public String admintouliaolist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/touliaolist";
	}
	
	//投料计划制定，当前计划内容
	@RequestMapping("/admintouliaodetail")
	public String admintouliaodetail(HttpServletRequest request, HttpServletResponse resp,
			String orderdata_id) 
			throws IOException {
		request.setAttribute("orderdata_id", orderdata_id);
		return "admin/touliaodetail";
	}
	
	//投料计划制定，当前计划内容
	@RequestMapping(value ="/admintouliaodetailajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String admintouliaodetailajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
			String orderdata_id) 
			throws IOException {
		List items = dt_orderlistservice.adminfindorderlist(orderdata_id);
//		System.out.println(items);
		List count = dt_orderlistservice.adminselectorderlistcount(orderdata_id);
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
		System.out.println(jobj);
		return jobj.toString();
	}
	
	//导入Excel表格
	@RequestMapping("/adminorderexcelimport")
	public String adminorderexcelimport(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/orderexcelimport";
	}
	//导入
	@RequestMapping(value = "/adminorderexcelimportajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminorderexcelimportajax(HttpServletRequest request, HttpServletResponse resp,@RequestParam("file") MultipartFile file) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		System.out.println("进入文件导入方法");
		JSONObject json = new JSONObject();
        //创建处理EXCEL的类
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        File xlsFile = new File("D:\\研一\\final\\程序\\202202100002.xls"); 
        try {
	        // 获得工作簿
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsFile));
	        // 获得工作表
	        HSSFSheet sheet = workbook.getSheetAt(0);
	        int rows = sheet.getPhysicalNumberOfRows();
	        System.out.println(rows);
	        for (int i = 0; i < rows; i++) {
	            // 获取第i行数据
	            HSSFRow sheetRow = sheet.getRow(i);
	            HSSFCell orderdata_id = sheetRow.getCell(0);
	            HSSFCell orcode1 = sheetRow.getCell(1);
	            HSSFCell cpname1 = sheetRow.getCell(2);
	            HSSFCell cpcode1 = sheetRow.getCell(3);
	            HSSFCell gjname1 = sheetRow.getCell(4);
	            HSSFCell gjcode1 = sheetRow.getCell(5);
	            HSSFCell gjnumb1 = sheetRow.getCell(6);
	            HSSFCell orstime1 = sheetRow.getCell(7);
	            HSSFCell oretime1 = sheetRow.getCell(8);
	            HSSFCell orstat1 = sheetRow.getCell(9);
	            HSSFCell gjstat1 = sheetRow.getCell(10);
	            
	            String orcode = String.valueOf(orcode1);
	            String cpname = String.valueOf(cpname1);
	            String cpcode = String.valueOf(cpcode1);
	            String gjname = String.valueOf(gjname1);
	            String gjcode = String.valueOf(gjcode1);
	            double midgjnumb = Double.valueOf(String.valueOf(gjnumb1));
	            System.out.println(midgjnumb);
	            int gjnumb = (int)midgjnumb;
	            System.out.println(gjnumb);
	            String orstime = String.valueOf(orstime1);
	            String oretime = String.valueOf(oretime1);
	            String orstat = String.valueOf(orstat1);
	            String gjstat = String.valueOf(gjstat1);
	            
	            dt_orderlist dataprocess = new dt_orderlist();
	            dataprocess.setorcode(orcode);
	            dataprocess.setcpname(cpname);
	            dataprocess.setcpcode(cpcode);
	            dataprocess.setgjname(gjname);
	            dataprocess.setgjcode(gjcode);
	            dataprocess.setgjnumb(gjnumb);
	            dataprocess.setorstime(orstime);
	            dataprocess.setoretime(oretime);
	            dataprocess.setorstat(orstat);
	            dataprocess.setgjstat(gjstat);
	            dt_orderlistservice.saveOrUpdate(dataprocess);
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
		
//		JSONObject json = new JSONObject();
//		DecimalFormat df = new DecimalFormat("#");
//		try {
//			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
//			String xlsfile = loadpath+"/"+filename;
//			System.out.println(xlsfile);
//			File xlsFile = new File(xlsfile);     
//	        // 获得工作簿
//			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsFile));
//	        // 获得工作表
//	        HSSFSheet sheet = workbook.getSheetAt(0);
//	        int rows = sheet.getPhysicalNumberOfRows();
//	        for (int i = 1; i < rows; i++) {
//	            // 获取第i行数据
//	            HSSFRow sheetRow = sheet.getRow(i);
//	            HSSFCell orderdata_id = sheetRow.getCell(0);
//	            HSSFCell orcode = sheetRow.getCell(1);
//	            HSSFCell cpname = sheetRow.getCell(2);
//	            HSSFCell cpcode = sheetRow.getCell(3);
//	            HSSFCell gjname = sheetRow.getCell(4);
//	            HSSFCell gjcode = sheetRow.getCell(5);
//	            HSSFCell gjnumb = sheetRow.getCell(6);
//	            HSSFCell orstime = sheetRow.getCell(7);
//	            HSSFCell oretime = sheetRow.getCell(8);
//	            HSSFCell orstat = sheetRow.getCell(9);
//	            HSSFCell gjstat = sheetRow.getCell(10);
//	            
//	            dt_orderlist dataprocess = dt_orderlistservice.get(Integer.valueOf(String.valueOf(orderdata_id)));
////		            dt_dataprocess dataprocess = new dt_dataprocess();
//	            dt_orderlistservice.saveOrUpdate(dataprocess);
//	        }
//			json.put("code", "100");
//			json.put("rinfo", "导入成功");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			json.put("code", "400");
//			json.put("rinfo", "系统错误");
//		}
//		return json.toString();
	}
	
	//Excel生成
	@RequestMapping(value = "/adminorderlistExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminorderlistExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("序号");
			header.add("订单编号");
			header.add("产品名称");
		    header.add("产品编号");
			header.add("工件名称");
		    header.add("工件编号");
		    header.add("工件数量");
		    header.add("订单到达时间");
		    header.add("订单完成时间");
		    header.add("订单状态");
		    header.add("工件状态");
		    
		    List items = dt_orderlistservice.adminfindallorderlist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("orderdata_id")));
				data.add(String.valueOf(map.get("orcode")));
				data.add(String.valueOf(map.get("cpname")));
				data.add(String.valueOf(map.get("cpcode")));
				data.add(String.valueOf(map.get("gjname")));
				data.add(String.valueOf(map.get("gjcode")));
				data.add(String.valueOf(map.get("gjnumb")));
				data.add(String.valueOf(map.get("orstime")));
				data.add(String.valueOf(map.get("oretime")));
				data.add(String.valueOf(map.get("orstat")));
				data.add(String.valueOf(map.get("gjstat")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"订单信息表.xls")){
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
