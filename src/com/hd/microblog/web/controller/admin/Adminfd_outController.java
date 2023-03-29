package com.hd.microblog.web.controller.admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hd.microblog.model.dt_admin;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.service.dt_allotrecordService;
import com.hd.microblog.model.dt_allotrecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class Adminfd_outController {
	
	@Autowired
	@Qualifier("dt_allotrecordService")
	private dt_allotrecordService dt_allotrecordservice;
	
	@Autowired
	@Qualifier("dt_sharedpartService")
	private dt_sharedpartService dt_sharedpartservice;
	
	//制定调拨记录表
	@RequestMapping("/adminfd_out")
	public String adminallotrecord(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/fd_out";
	}
	//制定年度供应计划表
	@RequestMapping(value = "/adminallotrecordajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminallotrecordajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
										String sparepartsNum,
										String sparepartsName,
										String sparepartsSpecification,
										String sparepartsType,
										String warehouseNum,
										String warehouseName,
										String warehouseFreight,
										String customerName,
										String ContractNum,
										String ContractName,
										String outdate,
										String auditor,
										String auditTime,
										String modifiedName,
										String modifiedDate,
										String modifiedTime,
										String preparationTime,
										String sort) throws IOException {
		System.out.println("lueluiele:");
		Integer start = Integer.valueOf(request.getParameter("start"));
		System.out.println(start);
		String length = request.getParameter("length");
		System.out.println(length);
		int number = Integer.valueOf(length);
		List items = dt_allotrecordservice.adminfindsupplyplanlist(sparepartsNum,
																	 sparepartsName,
																	 sparepartsSpecification,
																	 sparepartsType,
																	 warehouseNum,
																	 warehouseName,
																	 warehouseFreight,
																	 customerName,
																	 ContractNum,
																	 ContractName,
																	 outdate,
																	 auditor,
																	 auditTime,
																	 modifiedName,
																	 modifiedDate,
																	 modifiedTime,
																	 preparationTime,
																	sort,start,number);
		List count = dt_allotrecordservice.adminfindsupplyplanlistcount(sparepartsNum,
				sparepartsName,
				sparepartsSpecification,
				sparepartsType,
				warehouseNum,
				warehouseName,
				warehouseFreight,
				customerName,
				ContractNum,
				ContractName,
				outdate,
				auditor,
				auditTime,
				modifiedName,
				modifiedDate,
				modifiedTime,
				preparationTime);
		List<String> stringList = new ArrayList<>();
		for (Object obj : items) {
			stringList.add(obj.toString());
		}





		System.out.println("huoqushuju:");
		System.out.println(count);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		List returnlist  = new ArrayList();
		
		for(int i=0;i<stringList.size();i++){
			Map map = (Map)items.get(i);
			/*if(map.get("modifiedTime")!=null){
				map.put("modifiedTime",map.get("modifiedTime"));
				new java.util.Date(rs.getDate("date").getTime)
			}*/

			//将date类型处理为string，否则2json报错 使用Java 8中的stream和instanceof运算符
			map.put("czflag", 1);
			map.forEach((key, value) -> {
				if (value instanceof Date) { // 判断value是否为Date类型
					map.put(key, ((Date) value).toString()); // 将Date类型转换为String类型
				}
			});
			returnlist.add(map);

		}

		JSONObject jobj = new JSONObject();
				
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist.toArray());
		
		System.out.println("json为=");
		System.out.println(jobj);
		return jobj.toString();
	}
	/*@RequestMapping(value = "/adminfd_outdeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map adminfd_outdeleteajax(HttpServletRequest request,Integer id) throws IOException {
		Map map = new HashMap<String, String>();
		System.out.println(id);
		try{
			dt_allotrecord dt_allotrecord = dt_allotrecordservice.get(id);
			dt_allotrecord.setStatus(2);
				dt_allotrecordservice.saveOrUpdate(dt_allotrecord);
				map.put("code", "100");

		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
		}
		return map;
	}*/
	
	@RequestMapping("/adminallotrecordchange")
	public String adminapplyplanedit(HttpServletRequest request, HttpServletResponse resp,Integer allot_id,Integer sum_allot_number,String jqcode,String bdcode,String from_store,
			String qccode,String qcname,String this_allot_number) 
			throws IOException {
		
		request.setAttribute("allot_id", allot_id);
		request.setAttribute("sum_allot_number", sum_allot_number);
		request.setAttribute("old_allot_number", this_allot_number);
		request.setAttribute("jqcode", jqcode);
		request.setAttribute("bdcode", bdcode);
		request.setAttribute("from_store", from_store);
		request.setAttribute("qccode", qccode);
		request.setAttribute("qcname", qcname);
		request.setAttribute("this_allot_number", this_allot_number);


		return "admin/allotrecordchange";
	}
	
	@RequestMapping(value = "/adminallotrecordchangeajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminallotrecordchangeajax(HttpServletRequest request,HttpServletResponse resp,Integer allot_id,
			Integer sum_allot_number,Integer old_allot_number,String jqcode,String bdcode,String from_store,
			String qccode,String qcname,Integer this_allot_number) throws IOException {
		
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#");
		
		//不取值，直接更新数值
		try{
			//更新调拨记录表
			dt_allotrecordservice.refreshallotrecord(allot_id,jqcode,bdcode,from_store,qccode,qcname,this_allot_number,old_allot_number,sum_allot_number);
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
}
