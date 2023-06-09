package com.hd.microblog.web.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.list.FixedSizeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.common.util.MD5;

@Controller
public class AdminInformationController {
	
	
	//折线图
	@RequestMapping("/zhexiantu")
	public String zhexiantu(HttpServletRequest request, HttpServletResponse resp,Integer dataprocess_id)
		      throws IOException, SQLException {
		request.setAttribute("dataprocess_id", dataprocess_id);
		return "admin/charts/zhexiantu"; 	
	}
	//时间轴折线图
	@RequestMapping("/shijianzhouzhexiantu")
	public String shijianzhouzhexiantu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/shijianzhouzhexiantu"; 	
	}
	//区域图
	@RequestMapping("/quyutu")
	public String quyutu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/quyutu"; 	
	}
	//柱状图
	@RequestMapping("/zhuzhuangtu")
	public String zhuzhuangtu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/zhuzhuangtu"; 	
	}
	//饼状图
	@RequestMapping("/bingzhuangtu")
	public String bingzhuangtu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/bingzhuangtu"; 	
	}
	//3D柱状图
	@RequestMapping("/sanDzhuzhuangtu")
	public String sanDzhuzhuangtu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/sanDzhuzhuangtu"; 	
	}
	//3D饼状图
	@RequestMapping("/sanDbingzhuangtu")
	public String sanDbingzhuangtu(HttpServletRequest request, HttpServletResponse resp)
		      throws IOException, SQLException {
		return "admin/charts/sanDbingzhuangtu"; 	
	}
}









