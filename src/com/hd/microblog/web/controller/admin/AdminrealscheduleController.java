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

import java.text.SimpleDateFormat;

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

import com.hd.common.util.MD5;
import com.hd.microblog.model.dt_admin;
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.dt_orderlist;
import com.hd.microblog.model.dt_realschedule;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_realscheduleService;
import com.hd.microblog.util.createxls;

@Controller
public class AdminrealscheduleController {
	 
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_realscheduleService")
	private dt_realscheduleService dt_realscheduleservice;
	
	//调度方案管理
	@RequestMapping("/adminrealschedule")
	public String adminrealschedule(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/realschedule";
	}
	//工件信息表
	@RequestMapping(value = "/adminrealscheduleajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminrealscheduleajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String orcode,String gjcode,String opcode) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_realscheduleservice.adminfindrealschedulelist(orcode,gjcode,opcode,start,number);
		List count = dt_realscheduleservice.adminfindrealschedulelistcount(orcode,gjcode,opcode);
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
	//生产记录录入
	@RequestMapping("/adminaddrealschedule")
	public String adminaddrealschedule(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/addrealschedule";
	}
	
	//生产记录录入
	@RequestMapping("/adminrealscheduleedit")
	public String adminrealscheduleedit(HttpServletRequest request, HttpServletResponse resp, Integer realschedule_id) 
			throws IOException {
		dt_realschedule  dataprocess = dt_realscheduleservice.get(realschedule_id);
		request.setAttribute("realschedule_id", realschedule_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/realscheduleedit";
	}
	
	//添加生产记录
	@RequestMapping(value = "/adminaddrealscheduleajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminaddrealscheduleajax(HttpServletRequest request,String orcode,
			String cpcode,String gjcode,String opcode,String jqcode,String sttime,String edtime) throws IOException {
		Map map = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
//		double pctime = 100;
		List cpname_list = dt_realscheduleservice.adminfindcpname(cpcode);
		List gjname_list = dt_realscheduleservice.adminfindgjname(gjcode);
		List opname_list = dt_realscheduleservice.adminfindopname(opcode);
		List jqname_list = dt_realscheduleservice.adminfindjqname(jqcode);
		
		String cpname = (String)((Map)cpname_list.get(0)).get("cpname");
		String gjname = (String)((Map)gjname_list.get(0)).get("gjname");
		String opname = (String)((Map)opname_list.get(0)).get("opname");
		String jqname = (String)((Map)jqname_list.get(0)).get("machine_name");
		
		sttime = sttime.replace("T"," ");
		edtime = edtime.replace("T"," ");
		
		int hours = Integer.parseInt(edtime.substring(11,13))-Integer.parseInt(sttime.substring(11,13));
		int minite = Integer.parseInt(edtime.substring(14,16))-Integer.parseInt(sttime.substring(14,16));
		double pctime = hours*60 + minite;
		
		System.out.println(pctime);
		
		try{
			dt_realschedule realschedule = new dt_realschedule();
			realschedule.setorcode(orcode);
			realschedule.setcpcode(cpcode);
			realschedule.setcpname(cpname);
			realschedule.setgjcode(gjcode);
			realschedule.setgjname(gjname);
			realschedule.setopcode(opcode);
			realschedule.setopname(opname);
			realschedule.setjqcode(jqcode);
			realschedule.setjqname(jqname);
			realschedule.setsttime(sttime);
			realschedule.setedtime(edtime);
			realschedule.setpctime(pctime);
			dt_realscheduleservice.saveOrUpdate(realschedule);
			map.put("code", "100");
			map.put("info", "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "添加失败");
		}
		return map;
	}

	@RequestMapping(value = "/adminrealscheduleeditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminrealscheduleeditajax(HttpServletRequest request,Integer realschedule_id,String sttime, String edtime) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		sttime = sttime.replace("T"," ");
		edtime = edtime.replace("T"," ");
		try{        
			dt_realschedule  dataprocess = dt_realscheduleservice.get(realschedule_id);
	        dataprocess.setsttime(sttime);
	        dataprocess.setedtime(edtime);
	        dt_realscheduleservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	
	//导入Excel表格
	@RequestMapping("/adminrealscheduleexcelimport")
	public String adminrealscheduleexcelimport(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/realscheduleexcelimport";
	}
	//导入
	@RequestMapping(value = "/adminrealscheduleexcelimportajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminrealscheduleexcelimportajax(HttpServletRequest request, HttpServletResponse resp,@RequestParam("file") MultipartFile file) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		System.out.println("进入文件导入方法");
		JSONObject json = new JSONObject();
        //创建处理EXCEL的类
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        File xlsFile = new File("D:\\研一\\final\\程序\\生产记录.xls"); 
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
	            HSSFCell orcode1 = sheetRow.getCell(1);
	            HSSFCell cpname1 = sheetRow.getCell(2);
	            HSSFCell cpcode1 = sheetRow.getCell(3);
	            HSSFCell gjname1 = sheetRow.getCell(4);
	            HSSFCell gjcode1 = sheetRow.getCell(5);
	            HSSFCell opname1 = sheetRow.getCell(6);
	            HSSFCell opcode1 = sheetRow.getCell(7);
	            HSSFCell jqname1 = sheetRow.getCell(8);
	            HSSFCell jqcode1 = sheetRow.getCell(9);
	            HSSFCell sttime1 = sheetRow.getCell(10);
	            HSSFCell edtime1 = sheetRow.getCell(11);
	            HSSFCell pctime1 = sheetRow.getCell(12);
	            
	            String orcode = String.valueOf(orcode1);
	            String cpname = String.valueOf(cpname1);
	            String cpcode = String.valueOf(cpcode1);
	            String gjname = String.valueOf(gjname1);
	            String gjcode = String.valueOf(gjcode1);
	            String opname = String.valueOf(opname1);
	            String opcode = String.valueOf(opcode1);
	            String jqname = String.valueOf(jqname1);
	            String jqcode = String.valueOf(jqcode1);
	            String sttime = String.valueOf(sttime1);
	            String edtime = String.valueOf(edtime1);
	            double pctime = Double.valueOf(String.valueOf(pctime1));
	            
	            dt_realschedule dataprocess = new dt_realschedule();
	            dataprocess.setorcode(orcode);
	            dataprocess.setcpname(cpname);
	            dataprocess.setcpcode(cpcode);
	            dataprocess.setgjname(gjname);
	            dataprocess.setgjcode(gjcode);
	            dataprocess.setopname(opname);
	            dataprocess.setopcode(opcode);
	            dataprocess.setjqname(jqname);
	            dataprocess.setjqcode(jqcode);
	            dataprocess.setsttime(sttime);
	            dataprocess.setedtime(edtime);
	            dataprocess.setpctime(pctime);
	            
	            dt_realscheduleservice.saveOrUpdate(dataprocess);
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
//			File xlsFile = new File(xlsfile);     
//			/*
//			 * 1.XSSFWorkbook 
//			 * 2.HSSFWorkbook
//			 */
//	        // 获得工作簿
//			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsFile));
//
//	        // 获得工作表
//	        HSSFSheet sheet = workbook.getSheetAt(0);
//
//	        int rows = sheet.getPhysicalNumberOfRows();
//
//	        for (int i = 1; i < rows; i++) {
//	            // 获取第i行数据
//	            HSSFRow sheetRow = sheet.getRow(i);
//	            HSSFCell realschedule_id = sheetRow.getCell(0);
//	            HSSFCell orcode = sheetRow.getCell(1);
//	            HSSFCell cpname = sheetRow.getCell(2);
//	            HSSFCell cpcode = sheetRow.getCell(3);
//	            HSSFCell gjname = sheetRow.getCell(4);
//	            HSSFCell gjcode = sheetRow.getCell(5);
//	            HSSFCell opname = sheetRow.getCell(6);
//	            HSSFCell opcode = sheetRow.getCell(7);
//	            HSSFCell jqname = sheetRow.getCell(8);
//	            HSSFCell jqcode = sheetRow.getCell(9);
//	            HSSFCell sttime = sheetRow.getCell(10);
//	            HSSFCell edtime = sheetRow.getCell(11);
//	            HSSFCell pctime = sheetRow.getCell(12);
//	            
//	            dt_realschedule dataprocess = dt_realscheduleservice.get(Integer.valueOf(String.valueOf(realschedule_id)));
//	            dt_realscheduleservice.saveOrUpdate(dataprocess);
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
	
}


