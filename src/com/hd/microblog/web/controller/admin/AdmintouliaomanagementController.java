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

//进度条部分引用
import java.awt.Color; 
import java.awt.Toolkit; 
import javax.swing.ImageIcon; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JProgressBar; 
import javax.swing.JWindow;

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
import com.hd.microblog.model.dt_orderlist;
import com.hd.microblog.model.dt_touliaomanagement;
import com.hd.microblog.model.dt_prediction;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_touliaomanagementService;
import com.hd.microblog.util.createxls;

@Controller
public class AdmintouliaomanagementController {
	
	// 获取屏幕窗口大小 
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; 
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; 
	@SuppressWarnings("serial")
	private class Demo extends JWindow implements Runnable {
		//画图的全局变量
		// 定义加载窗口大小 
		public static final int LOAD_WIDTH = 455; 
		public static final int LOAD_HEIGHT = 295; 
		// 定义进度条组件 
		public JProgressBar progressbar; 
		// 定义标签组件 
		public JLabel label;
		//进度条部分方法
		// 构造函数
		public Demo() {
			// 创建标签,并在标签上放置一张图片
			label = new JLabel(new ImageIcon("images/background.jpg"));
			label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);
			// 创建进度条
			progressbar = new JProgressBar();
			// 显示当前进度值信息
			progressbar.setStringPainted(true);
			// 设置进度条边框不显示
			progressbar.setBorderPainted(false);
			// 设置进度条的前景色
			progressbar.setForeground(new Color(0, 210, 40));
			// 设置进度条的背景色
			progressbar.setBackground(new Color(188, 190, 194));
			progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);
			// 添加组件
			this.add(label);
			this.add(progressbar);
			// 设置布局为空
			this.setLayout(null);
			// 设置窗口初始位置
			this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);
			// 设置窗口大小
			this.setSize(LOAD_WIDTH, LOAD_HEIGHT);
			// 设置窗口显示
			this.setVisible(true);
	 
		}
	 
		public void main(String[] args) {
			Demo t = new Demo();
			new Thread(t).start();
		}
	 
		@Override
		public void run() {
	 
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressbar.setValue(i);
			}
			JOptionPane.showMessageDialog(this, "加载完成");
			this.dispose();
	 
		}
	}
	
	@Autowired
	@Qualifier("dt_adminService")
	private dt_adminService dt_adminservice;
	@Autowired
	@Qualifier("dt_touliaomanagementService")
	private dt_touliaomanagementService dt_touliaomanagementservice;
	
	//订单信息表
	@RequestMapping("/admintouliaomanagement")
	public String admintouliaomanagement(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/touliaomanagement";
	}
	//投料信息表
	@RequestMapping(value = "/admintouliaomanagementajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String admintouliaomanagementajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw, 
			String orcode,String cpname,String cpcode,String gjname,String gjcode,String touliaostate) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));  
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_touliaomanagementservice.adminfindtouliaomanagement(orcode,cpname,cpcode,gjname,gjcode,touliaostate,start, number);
		List count = dt_touliaomanagementservice.adminfindtouliaomanagementcount(orcode,cpname,cpcode,gjname,gjcode,touliaostate);
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
	
	//制定投料计划
	@RequestMapping(value = "/touliao_plan", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String touliao_plan(HttpServletRequest request,HttpServletResponse resp, String orderdata_id) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
//		String[] args1 = new String[] {"cmd /k start python","D:\\研一\\final\\程序\\job_release\\release_ga_test.py"}; 
//		Process proc = Runtime.getRuntime().exec(args1);
		String sql_list = "cmd /k start python D:\\研一\\final\\程序\\job_release\\release_ga_test.py" + " " + orderdata_id;
//		sql_list = "\""+sql_list+"\"";
		System.out.println(sql_list);
//		Process proc = Runtime.getRuntime().exec("cmd /k start python D:\\研一\\final\\程序\\job_release\\release_ga_test.py 0,1,2");
		Process proc = Runtime.getRuntime().exec(sql_list);
		while (proc.isAlive()==false) {
			break;
		}
		System.out.println(orderdata_id);
		json.put("code", "100");
		json.put("info", "生成成功");
		return json.toString();
	}

	//投料信息修改
	@RequestMapping("/admintouliaoedit")
	public String admintouliaoedit(HttpServletRequest request, HttpServletResponse resp,
			Integer touliao_id) 
			throws IOException {
		dt_touliaomanagement dataprocess = dt_touliaomanagementservice.get(touliao_id);
		request.setAttribute("touliao_id", touliao_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/touliaoedit";
	}

	@RequestMapping(value = "/admintouliaoeditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map admintouliaoeditajax(HttpServletRequest request,Integer touliao_id,Integer touliaonum) throws IOException {
		HttpSession session = request.getSession();
		Map map = new HashMap<String, String>();
		try{        
			dt_touliaomanagement  dataprocess = dt_touliaomanagementservice.get(touliao_id);
	        dataprocess.settouliaonum(touliaonum);
	        dt_touliaomanagementservice.saveOrUpdate(dataprocess);
	        
			map.put("code", "100");
			map.put("info", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", "400");
			map.put("info", "修改失败");
		}
		return map;
	}
	
	//调度计划制定，显示未调度的投料计划
	@RequestMapping("/adminschedulemake")
	public String adminschedulemake(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/schedulemake";
	}
	
	//调度方案执行
	@RequestMapping(value = "/scheduling_plan", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String scheduling_plan(HttpServletRequest request,HttpServletResponse resp, String orderdata_id) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
//		Process proc = Runtime.getRuntime().exec("cmd /k start python D:\\研一\\final\\程序\\part1\\moead-system1\\moead.py");
//		Process proc = Runtime.getRuntime().exec("cmd /c start /b python D:\\研一\\final\\程序\\part2\\moead-system2\\moead.py");
		String sql_list = "cmd /k start python D:\\研一\\final\\程序\\part1\\moead-system1\\moead.py 12,13";
		System.out.println(sql_list);
		Process proc = Runtime.getRuntime().exec(sql_list);
		while (proc.isAlive()==false) {
			break;
		}
//		try{
//			proc.waitFor();
//			System.out.println("加载中...");
//		}catch(Exception e){
//			e.printStackTrace();
//			json.put("code", "400");
//			json.put("info", "系统错误");
//		}
//		json.put("code", "100");
//		json.put("info", "生成成功");
		System.out.println("生成成功");
		json.put("code", "100");
		json.put("info", "生成成功");
		return json.toString();
	}	
	
	//Excel生成
	@RequestMapping(value = "/admintouliaomanagementExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String admintouliaomanagementExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
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
		    header.add("投料数量");
		    header.add("投料时间");
		    header.add("投料状态");

		    
		    List items = dt_touliaomanagementservice.adminfindalltouliaomanagement();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("touliao_id")));
				data.add(String.valueOf(map.get("orcode")));
				data.add(String.valueOf(map.get("cpname")));
				data.add(String.valueOf(map.get("cpcode")));
				data.add(String.valueOf(map.get("gjname")));
				data.add(String.valueOf(map.get("gjcode")));
				data.add(String.valueOf(map.get("touliaonum")));
				data.add(String.valueOf(map.get("touliaotime")));
				data.add(String.valueOf(map.get("touliaostate")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"投料计划表.xls")){
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
