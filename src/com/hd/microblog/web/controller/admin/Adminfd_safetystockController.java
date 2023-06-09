package com.hd.microblog.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hd.microblog.service.fd_outtempService;
import com.hd.microblog.service.fd_intempService;
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

import com.hd.microblog.model.fd_safetystock;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.fd_safetystockService;
import com.hd.microblog.util.createxls;

@Controller
public class Adminfd_safetystockController {
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("fd_safetystockService")
	private fd_safetystockService fd_safetystockservice;

	@Autowired
	@Qualifier("fd_outtempService")
	private fd_outtempService fd_outtempService;

	@Autowired
	@Qualifier("fd_intempService")
	private fd_intempService fd_intempservice;
	
	//数据处理表
	@RequestMapping("/adminfd_safetystocklist")
	public String adminfd_safetystocklist(HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		return "admin/fd_safetystocklist";
	}

	//数据处理表
	@RequestMapping(value = "/safetystockzhexiantuajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String zhexiantuajax(HttpServletRequest request, HttpServletResponse resp,int dataprocess_id) throws IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		try {
			System.out.println(dataprocess_id);

			List items1  = fd_outtempService.getBySparepartnum(dataprocess_id);
			List items2  = fd_intempservice.getBySparepartnum(dataprocess_id);
			List items3  = fd_safetystockservice.admincalcinventory(dataprocess_id);
			List items4  = fd_safetystockservice.admingetss(dataprocess_id);
			System.out.println("ss："+items4);
			//真实库存

			json.accumulate("items1", items1);
			json.accumulate("items2", items2);
			json.accumulate("items3", items3);
			json.accumulate("items4", items4);

			//预测库存
			/*List ydlist = fd_outservice.adminfindpredictionfordidandtype(dataprocess_id,1);
			Map ydmap = (Map)ydlist.get(0);
			List ydpjlist  = new ArrayList();
			ydpjlist.add(Double.valueOf(String.valueOf(ydmap.get("ycz1"))));

			System.out.println("====2"+ydpjlist);
			//不包含紧急补货库存
			List zslist = dt_predictionservice.adminfindpredictionfordidandtype(dataprocess_id,2);
			Map zsmap = (Map)zslist.get(0);
			List zsphlist  = new ArrayList();
			zsphlist.add(Double.valueOf(String.valueOf(zsmap.get("ycz1"))));

			System.out.println("====3"+zsphlist);

			json.put("zszlist", zszlist);
			json.put("ydpjlist", ydpjlist);
			json.put("zsphlist", zsphlist);*/


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return json.toString();
	}

	//数据处理表
	@RequestMapping(value = "/fd_safetystocklistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminfd_safetystocklistajax(HttpServletRequest request, Model model, String page, String rows , Integer draw,
											  String sparepartNum, String sparepartName, String sparepartSpecification,
											   String ss, String R, String maxInventory,String sstime,
											   String sort) throws IOException {
		System.out.println("进入Adminfd_safetystockController");


		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = fd_safetystockservice.adminfindfd_safetystocklist(sparepartNum,sparepartName,  sparepartSpecification,ss, R,  maxInventory, sstime,
				sort,start,number);
		List count = fd_safetystockservice.adminfindfd_safetystocklistcount(sparepartNum,sparepartName,  sparepartSpecification,ss, R,  maxInventory, sstime);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			map.put("czflag", 1);
			returnlist.add(map);
		}
		
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}	
	
	//计划调拨数量修改
	@RequestMapping("/adminstorehouseedit")
	public String adminstorehouseedit(HttpServletRequest request, HttpServletResponse resp,String jqcode,String bdcode,String zbcode,String qccode,String qcname,String unit,String unitprice,String dzys,Integer currentinventory) 
			throws IOException {						
		request.setAttribute("bdcode", bdcode);
		request.setAttribute("qccode", qccode);
		request.setAttribute("qcname", qcname);
		request.setAttribute("jqcode", jqcode);
		request.setAttribute("zbcode", zbcode);
		request.setAttribute("unit", unit);
		request.setAttribute("unitprice", unitprice);
		request.setAttribute("dzys", dzys);
		request.setAttribute("currentinventory", currentinventory);
		String[] testdata={"1","2","3","4","5"};
		request.setAttribute("testdata", testdata);
		System.out.println(qcname);
		//System.out.println(jqcode+bdcode+zbcode+qccode+qcname+currentinventory);
		return "admin/storehouseedit";
	}		
	
	@RequestMapping(value = "/queryDesbdcode",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryDesbdcode(HttpServletRequest request,HttpServletResponse resp,String jqcode,String qccode) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		request.setAttribute("jqcode", jqcode);
		request.setAttribute("qccode", qccode);
		System.out.println(jqcode+qccode);

		List bdcodeList = fd_safetystockservice.querybdcode(jqcode,qccode);
		List returnlist  = new ArrayList();
		for(int i=0;i<bdcodeList.size();i++){
			Map map = (Map)bdcodeList.get(i);
			returnlist.add(map);
		}
		System.out.println(returnlist);
		JSONObject jobj = new JSONObject();
		jobj.accumulate("resData", returnlist);
		return jobj.toString();
	}
	
	@RequestMapping(value = "/admindeseditajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String admindeseditajax(HttpServletRequest request,HttpServletResponse resp,String desbdcode) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		System.out.println(desbdcode);
		json.put("code", "100");
		json.put("info", "更新成功");
		return json.toString();
		
	}
	@RequestMapping(value = "/adminstorehouseeditajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminstorehouseeditajax(HttpServletRequest request,HttpServletResponse resp,String jqcode,String bdcode,String desbdcode,String zbcode,String qccode,String qcname,String unit,String unitprice,String dzys,Integer currentinventory,Integer recordnumber,String create_people,String createtime,String receive_people) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		System.out.println(desbdcode);
		//取对应目标仓库的当前库存
		List list = fd_safetystockservice.desstorecount(jqcode,desbdcode,qccode,qcname);
		System.out.println(list);
		int desstorecountnumber=0;
		Integer new_store = 0;
		Integer old_store = 0;
		old_store = currentinventory-recordnumber;
		request.setAttribute("qcname", qcname);
		if (list != null && list.size() != 0) {
			Map map2 = (Map)list.get(0);
			desstorecountnumber = (int) map2.get("currentinventory");
			System.out.println(desbdcode+bdcode);
			new_store = desstorecountnumber+recordnumber;
			System.out.println(new_store+old_store);
			try{				
				//使用更新方法
				fd_safetystockservice.updatestorehouse(jqcode,bdcode,desbdcode,qccode,qcname,new_store,old_store);
				//插入仓库调拨记录表数据
				fd_safetystockservice.insertstorerecord(jqcode,bdcode,desbdcode,zbcode,qccode,qcname,recordnumber,create_people,createtime,receive_people);
				
				json.put("code", "100");
				json.put("info", "更新成功");
			}catch(Exception e){
				e.printStackTrace();
				json.put("code", "400");
				json.put("info", "系统错误");
			}
		}else{
			//即原来的库存表中该仓库没有货物
			new_store=recordnumber;
			try{
				//使用插入方法插入新的库存记录
				fd_safetystockservice.insertstorehouse(jqcode,bdcode,desbdcode,zbcode,qccode,qcname,unit,unitprice,dzys,new_store,old_store);
				//插入仓库调拨记录表数据
				fd_safetystockservice.insertstorerecord(jqcode,bdcode,desbdcode,zbcode,qccode,qcname,recordnumber,create_people,createtime,receive_people);
				
				json.put("code", "100");
				json.put("info", "更新成功");
			}catch(Exception e){
				e.printStackTrace();
				json.put("code", "400");
				json.put("info", "系统错误");
			}
		}		
		return json.toString();
	}
	
	
	//Excel生成
	@RequestMapping(value = "/adminstorehouseExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminstorehouseExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("ID");
			header.add("战区编号");
		    header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("单位");
		    header.add("单价");
		    header.add("单装用数");
		    header.add("期初库存");
		    header.add("实时库存");
		    
		    List items = fd_safetystockservice.adminfindfd_safetystocklist();
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
				data.add(String.valueOf(map.get("initinventory")));
				data.add(String.valueOf(map.get("currentinventory")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"库存记录.xls")){
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
	@RequestMapping("/adminstorehouseexcelimport")
	public String adminstorehouseexcelimport(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/storehouseexcelimport";
	}
//	//导入
	@RequestMapping(value = "/adminstorehouseexcelimportajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminstorehouseexcelimportajax(HttpServletRequest request, HttpServletResponse resp,String filename) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		String sql="delete from dt_storehouse where 1=1";
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
	            HSSFCell initinventory = sheetRow.getCell(9);
	            HSSFCell currentinventory = sheetRow.getCell(10);
	            
	            
	            fd_safetystock dataprocess = fd_safetystockservice.get(Integer.valueOf(String.valueOf(id)));
	            
	            dataprocess.setJqcode(String.valueOf(jqcode));
	            dataprocess.setBdcode(String.valueOf(bdcode));
	            dataprocess.setZbcode(String.valueOf(zbcode));
	            dataprocess.setQccode(String.valueOf(qccode));
	            dataprocess.setQcname(String.valueOf(qcname));
	            dataprocess.setUnit(String.valueOf(unit));
	            dataprocess.setUnitprice(Double.valueOf(String.valueOf(unitprice)));
	            dataprocess.setDzys(Integer.valueOf(String.valueOf(dzys)));
	            dataprocess.setInitinventory(Integer.valueOf(String.valueOf(initinventory)));
	            dataprocess.setCurrentinventory(Integer.valueOf(String.valueOf(currentinventory)));
	            
	            //dt_storehouseservice.deleteTable(sql);
	            fd_safetystockservice.saveOrUpdate(dataprocess);
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

	//仓库调拨记录表
	@RequestMapping("/adminstorerecordlist")
	public String adminstorerecordlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/storerecordlist";
	}
	//仓库调拨记录表
	@RequestMapping(value = "/adminstorerecordlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminstorerecordlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String jqcode,String from_store,String to_store,String qccode,String qcname,String sort) throws IOException {
			
		Integer start = Integer.valueOf(request.getParameter("start"));  
		String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = fd_safetystockservice.adminfindstorerecordlist(jqcode,from_store,to_store,qccode,qcname,sort,start,number);
		List count = fd_safetystockservice.adminfindstorerecordlistcount(jqcode,from_store,to_store,qccode,qcname);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			map.put("czflag", 1);
			returnlist.add(map);
		}
			
		JSONObject jobj = new JSONObject();
			
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}
	
	@RequestMapping("/adminstorerecordchange")
	public String adminapplyplanedit(HttpServletRequest request, HttpServletResponse resp,Integer allot_id,String jqcode,String from_store,
			String to_store,String qccode,String qcname,Integer this_allot_number) 
			throws IOException {
		
		request.setAttribute("allot_id", allot_id);
		request.setAttribute("old_allot_number", this_allot_number);
		request.setAttribute("jqcode", jqcode);
		request.setAttribute("from_store", from_store);
		request.setAttribute("to_store", to_store);
		request.setAttribute("qccode", qccode);
		request.setAttribute("qcname", qcname);
		request.setAttribute("this_allot_number", this_allot_number);
		//取对应调出仓库的当前库存
		List list = fd_safetystockservice.desstorecount(jqcode,from_store,qccode,qcname);
		int desstorecountnumber=0;
		if (list != null && list.size() != 0) {
			Map map2 = (Map)list.get(0);
			desstorecountnumber = (int) map2.get("currentinventory");
			System.out.println(desstorecountnumber);
		}
		request.setAttribute("from_store_currentinventory", desstorecountnumber);
		
		return "admin/storerecordchange";
	}
	
	@RequestMapping(value = "/adminstorerecordchangeajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminstorerecordchangeajax(HttpServletRequest request,HttpServletResponse resp,Integer allot_id,
			Integer old_allot_number,String jqcode,String from_store,String to_store,
			String qccode,String qcname,Integer this_allot_number) throws IOException {
		
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		
		//不取值，直接更新数值
		try{
			//更新调拨记录表
			fd_safetystockservice.refreshstorerecord(allot_id,jqcode,from_store,to_store,qccode,qcname,this_allot_number,old_allot_number);
			//更新当前库存
			//dt_allotrecordservice.refreshstorehouse(jqcode,from_store,qccode,qcname,this_allot_number,old_allot_number);
			json.put("code", "100");
			json.put("info", "更新成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}
	
	//Excel生成
		@RequestMapping(value = "/adminstorerecordExcel",produces = "application/json; charset=utf-8")
		@ResponseBody
		private String adminstorerecordExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
			HttpSession session = request.getSession();
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
			JSONObject json = new JSONObject();
			try{
				//生成xls表头
				List<String> header = new ArrayList<String>(); // 第一行数据
				List<List<String>> body = new ArrayList<List<String>>();
				header.add("ID");
				header.add("战区编号");
				header.add("调出仓库编号");
			    header.add("调入仓库编号");
				header.add("装备代码");
			    header.add("器材代码");
			    header.add("器材名称");
			    header.add("调拨数量");
			    header.add("计划人");
			    header.add("计划时间");
			    header.add("领用人");
			    
			    List items = fd_safetystockservice.adminfindstorerecordlist();
				for(int i=0;i<items.size();i++){
					Map map = (Map)items.get(i);
					//添加xls信息
					List<String> data = new ArrayList<String>();
					data.add(String.valueOf(map.get("allot_id")));
					data.add(String.valueOf(map.get("jqcode")));
					data.add(String.valueOf(map.get("from_store")));
					data.add(String.valueOf(map.get("to_store")));
					data.add(String.valueOf(map.get("zbcode")));
					data.add(String.valueOf(map.get("qccode")));
					data.add(String.valueOf(map.get("qcname")));
					data.add(String.valueOf(map.get("this_allot_number")));
					data.add(String.valueOf(map.get("create_people")));
					data.add(String.valueOf(map.get("createtime")));
					data.add(String.valueOf(map.get("receive_people")));
			    	body.add(data);
			    	
				}
				//xls输出
				String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
			    //新建文件路径
			    File file2 = new File(loadpath);
				if (!file2.exists()) {
					file2.mkdir();
				}
				try(OutputStream out = new FileOutputStream(loadpath+"/"+"仓库调整记录-战区.xls")){
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
