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

import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.model.dt_prediction;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_adminService;
import com.hd.microblog.service.dt_dataprocessService;
import com.hd.microblog.service.dt_predictionService;
import com.hd.microblog.service.dt_sharedpartService;
import com.hd.microblog.util.createxls;




@Controller
public class AdminpredictionController {
	 
	@Autowired
	@Qualifier("dt_predictionService")
	private dt_predictionService dt_predictionservice;
	@Autowired
	@Qualifier("dt_dataprocessService")
	private dt_dataprocessService dt_dataprocessservice;
	@Autowired
	@Qualifier("dt_sharedpartService")
	private dt_sharedpartService dt_sharedpartservice;
	
	//预测查询表
	@RequestMapping("/adminpredictionlist")
	public String adminpredictionlist(HttpServletRequest request, HttpServletResponse resp) 
			throws IOException {
		return "admin/predictionlist";
	}
	//预测查询表
	@RequestMapping(value = "/adminpredictionlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminpredictionlistajax(HttpServletRequest request, Model model, String page, String rows ,Integer draw,
			String jqcode,String bdcode,String zbcode,String qccode,String qcname,String sort) throws IOException {
		
		Integer start = Integer.valueOf(request.getParameter("start"));
	    String length = request.getParameter("length");  
		int number = Integer.valueOf(length);
		List items = dt_predictionservice.adminfindpredictionlist(jqcode,bdcode,zbcode,qccode,qcname,sort,start,number);
		List count = dt_predictionservice.adminfindpredictionlistcount(jqcode,bdcode,zbcode,qccode,qcname);
		int countnumber = 0;
		if (count != null && count.size() != 0) {
			Map map = (Map) count.get(0);
			countnumber = Integer.valueOf(String.valueOf(map.get("count")));
		}
		List returnlist  = new ArrayList();
		for(int i=0;i<items.size();i++){
			Map map = (Map)items.get(i);
			if(Integer.valueOf(String.valueOf(map.get("ycff")))==1) {
				map.put("ycffname", "简单移动平均法");
			}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==2) {
				map.put("ycffname", "指数平滑法");
			}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==3) {
				map.put("ycffname", "线性回归法");
			}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==4) {
				map.put("ycffname", "误差平方和倒数组合预测");
			}
			returnlist.add(map);
		}
		JSONObject jobj = new JSONObject();
		
		jobj.accumulate("draw", draw);
		jobj.accumulate("recordsFiltered", countnumber);
		jobj.accumulate("recordsTotal", countnumber);
		jobj.accumulate("data", returnlist);
		return jobj.toString();
	}
	//统计
	@RequestMapping(value = "/adminpredictionstatistics",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminpredictionstatistics(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			List list = dt_predictionservice.adminfindpredictionlistall();
			for(int i=0;i<list.size();i++){
				Map map = (Map)list.get(i);
				
				dt_prediction prediction = dt_predictionservice.get(Integer.valueOf(String.valueOf(map.get("prediction_id"))));
				if(prediction.getYcff()==1) {
					Map returnmap1 = (Map)Standarddeviation(prediction.getDataprocess_id(),1);
					Map returnmap2 = (Map)Standarddeviation(prediction.getDataprocess_id(),2);
					Map returnmap3 = (Map)Standarddeviation(prediction.getDataprocess_id(),3);
					Map returnmap4 = (Map)Standarddeviation(prediction.getDataprocess_id(),4);
					Map returnmap5 = (Map)Standarddeviation(prediction.getDataprocess_id(),5);
					Map returnmap6 = (Map)Standarddeviation(prediction.getDataprocess_id(),6);
					Map returnmap7 = (Map)Standarddeviation(prediction.getDataprocess_id(),7);
					Map returnmap8 = (Map)Standarddeviation(prediction.getDataprocess_id(),8);
					Map returnmap9 = (Map)Standarddeviation(prediction.getDataprocess_id(),9);
					Map returnmap10 = (Map)Standarddeviation(prediction.getDataprocess_id(),10);
					Map returnmap11 = (Map)Standarddeviation(prediction.getDataprocess_id(),11);
					Map returnmap12 = (Map)Standarddeviation(prediction.getDataprocess_id(),12);
					//预测值
					prediction.setYcz1(String.valueOf(returnmap1.get("Average")));
					prediction.setYcz2(String.valueOf(returnmap2.get("Average")));
					prediction.setYcz3(String.valueOf(returnmap3.get("Average")));
					prediction.setYcz4(String.valueOf(returnmap4.get("Average")));
					prediction.setYcz5(String.valueOf(returnmap5.get("Average")));
					prediction.setYcz6(String.valueOf(returnmap6.get("Average")));
					prediction.setYcz7(String.valueOf(returnmap7.get("Average")));
					prediction.setYcz8(String.valueOf(returnmap8.get("Average")));
					prediction.setYcz9(String.valueOf(returnmap9.get("Average")));
					prediction.setYcz10(String.valueOf(returnmap10.get("Average")));
					prediction.setYcz11(String.valueOf(returnmap11.get("Average")));
					prediction.setYcz12(String.valueOf(returnmap12.get("Average")));
					//标准差
					prediction.setBzc1(String.valueOf(returnmap1.get("result")));
					prediction.setBzc2(String.valueOf(returnmap2.get("result")));
					prediction.setBzc3(String.valueOf(returnmap3.get("result")));
					prediction.setBzc4(String.valueOf(returnmap4.get("result")));
					prediction.setBzc5(String.valueOf(returnmap5.get("result")));
					prediction.setBzc6(String.valueOf(returnmap6.get("result")));
					prediction.setBzc7(String.valueOf(returnmap7.get("result")));
					prediction.setBzc8(String.valueOf(returnmap8.get("result")));
					prediction.setBzc9(String.valueOf(returnmap9.get("result")));
					prediction.setBzc10(String.valueOf(returnmap10.get("result")));
					prediction.setBzc11(String.valueOf(returnmap11.get("result")));
					prediction.setBzc12(String.valueOf(returnmap12.get("result")));
					//真实值
					prediction.setZsz1(String.valueOf(returnmap1.get("real")));
					prediction.setZsz2(String.valueOf(returnmap2.get("real")));
					prediction.setZsz3(String.valueOf(returnmap3.get("real")));
					prediction.setZsz4(String.valueOf(returnmap4.get("real")));
					prediction.setZsz5(String.valueOf(returnmap5.get("real")));
					prediction.setZsz6(String.valueOf(returnmap6.get("real")));
					prediction.setZsz7(String.valueOf(returnmap7.get("real")));
					prediction.setZsz8(String.valueOf(returnmap8.get("real")));
					prediction.setZsz9(String.valueOf(returnmap9.get("real")));
					prediction.setZsz10(String.valueOf(returnmap10.get("real")));
					prediction.setZsz11(String.valueOf(returnmap11.get("real")));
					prediction.setZsz12(String.valueOf(returnmap12.get("real")));
					//方差
					prediction.setFx1(String.valueOf(returnmap1.get("fx")));
					prediction.setFx2(String.valueOf(returnmap2.get("fx")));
					prediction.setFx3(String.valueOf(returnmap3.get("fx")));
					prediction.setFx4(String.valueOf(returnmap4.get("fx")));
					prediction.setFx5(String.valueOf(returnmap5.get("fx")));
					prediction.setFx6(String.valueOf(returnmap6.get("fx")));
					prediction.setFx7(String.valueOf(returnmap7.get("fx")));
					prediction.setFx8(String.valueOf(returnmap8.get("fx")));
					prediction.setFx9(String.valueOf(returnmap9.get("fx")));
					prediction.setFx10(String.valueOf(returnmap10.get("fx")));
					prediction.setFx11(String.valueOf(returnmap11.get("fx")));
					prediction.setFx12(String.valueOf(returnmap12.get("fx")));
				}
				if(prediction.getYcff()==2) {
					Map returnmap1 = (Map)Standarddeviation2(prediction.getDataprocess_id(),1);
					Map returnmap2 = (Map)Standarddeviation2(prediction.getDataprocess_id(),2);
					Map returnmap3 = (Map)Standarddeviation2(prediction.getDataprocess_id(),3);
					Map returnmap4 = (Map)Standarddeviation2(prediction.getDataprocess_id(),4);
					Map returnmap5 = (Map)Standarddeviation2(prediction.getDataprocess_id(),5);
					Map returnmap6 = (Map)Standarddeviation2(prediction.getDataprocess_id(),6);
					Map returnmap7 = (Map)Standarddeviation2(prediction.getDataprocess_id(),7);
					Map returnmap8 = (Map)Standarddeviation2(prediction.getDataprocess_id(),8);
					Map returnmap9 = (Map)Standarddeviation2(prediction.getDataprocess_id(),9);
					Map returnmap10 = (Map)Standarddeviation2(prediction.getDataprocess_id(),10);
					Map returnmap11 = (Map)Standarddeviation2(prediction.getDataprocess_id(),11);
					Map returnmap12 = (Map)Standarddeviation2(prediction.getDataprocess_id(),12);
					//预测值
					prediction.setYcz1(String.valueOf(returnmap1.get("Average")));
					prediction.setYcz2(String.valueOf(returnmap2.get("Average")));
					prediction.setYcz3(String.valueOf(returnmap3.get("Average")));
					prediction.setYcz4(String.valueOf(returnmap4.get("Average")));
					prediction.setYcz5(String.valueOf(returnmap5.get("Average")));
					prediction.setYcz6(String.valueOf(returnmap6.get("Average")));
					prediction.setYcz7(String.valueOf(returnmap7.get("Average")));
					prediction.setYcz8(String.valueOf(returnmap8.get("Average")));
					prediction.setYcz9(String.valueOf(returnmap9.get("Average")));
					prediction.setYcz10(String.valueOf(returnmap10.get("Average")));
					prediction.setYcz11(String.valueOf(returnmap11.get("Average")));
					prediction.setYcz12(String.valueOf(returnmap12.get("Average")));
					//标准差
					prediction.setBzc1(String.valueOf(returnmap1.get("result")));
					prediction.setBzc2(String.valueOf(returnmap2.get("result")));
					prediction.setBzc3(String.valueOf(returnmap3.get("result")));
					prediction.setBzc4(String.valueOf(returnmap4.get("result")));
					prediction.setBzc5(String.valueOf(returnmap5.get("result")));
					prediction.setBzc6(String.valueOf(returnmap6.get("result")));
					prediction.setBzc7(String.valueOf(returnmap7.get("result")));
					prediction.setBzc8(String.valueOf(returnmap8.get("result")));
					prediction.setBzc9(String.valueOf(returnmap9.get("result")));
					prediction.setBzc10(String.valueOf(returnmap10.get("result")));
					prediction.setBzc11(String.valueOf(returnmap11.get("result")));
					prediction.setBzc12(String.valueOf(returnmap12.get("result")));
					//真实值
					prediction.setZsz1(String.valueOf(returnmap1.get("real")));
					prediction.setZsz2(String.valueOf(returnmap2.get("real")));
					prediction.setZsz3(String.valueOf(returnmap3.get("real")));
					prediction.setZsz4(String.valueOf(returnmap4.get("real")));
					prediction.setZsz5(String.valueOf(returnmap5.get("real")));
					prediction.setZsz6(String.valueOf(returnmap6.get("real")));
					prediction.setZsz7(String.valueOf(returnmap7.get("real")));
					prediction.setZsz8(String.valueOf(returnmap8.get("real")));
					prediction.setZsz9(String.valueOf(returnmap9.get("real")));
					prediction.setZsz10(String.valueOf(returnmap10.get("real")));
					prediction.setZsz11(String.valueOf(returnmap11.get("real")));
					prediction.setZsz12(String.valueOf(returnmap12.get("real")));
					//方差
					prediction.setFx1(String.valueOf(returnmap1.get("fx")));
					prediction.setFx2(String.valueOf(returnmap2.get("fx")));
					prediction.setFx3(String.valueOf(returnmap3.get("fx")));
					prediction.setFx4(String.valueOf(returnmap4.get("fx")));
					prediction.setFx5(String.valueOf(returnmap5.get("fx")));
					prediction.setFx6(String.valueOf(returnmap6.get("fx")));
					prediction.setFx7(String.valueOf(returnmap7.get("fx")));
					prediction.setFx8(String.valueOf(returnmap8.get("fx")));
					prediction.setFx9(String.valueOf(returnmap9.get("fx")));
					prediction.setFx10(String.valueOf(returnmap10.get("fx")));
					prediction.setFx11(String.valueOf(returnmap11.get("fx")));
					prediction.setFx12(String.valueOf(returnmap12.get("fx")));
				}
				if(prediction.getYcff()==3) {
					Map returnmap1 = (Map)Standarddeviation3(prediction.getDataprocess_id(),1);
					Map returnmap2 = (Map)Standarddeviation3(prediction.getDataprocess_id(),2);
					Map returnmap3 = (Map)Standarddeviation3(prediction.getDataprocess_id(),3);
					Map returnmap4 = (Map)Standarddeviation3(prediction.getDataprocess_id(),4);
					Map returnmap5 = (Map)Standarddeviation3(prediction.getDataprocess_id(),5);
					Map returnmap6 = (Map)Standarddeviation3(prediction.getDataprocess_id(),6);
					Map returnmap7 = (Map)Standarddeviation3(prediction.getDataprocess_id(),7);
					Map returnmap8 = (Map)Standarddeviation3(prediction.getDataprocess_id(),8);
					Map returnmap9 = (Map)Standarddeviation3(prediction.getDataprocess_id(),9);
					Map returnmap10 = (Map)Standarddeviation3(prediction.getDataprocess_id(),10);
					Map returnmap11 = (Map)Standarddeviation3(prediction.getDataprocess_id(),11);
					Map returnmap12 = (Map)Standarddeviation3(prediction.getDataprocess_id(),12);
					//预测值
					prediction.setYcz1(String.valueOf(returnmap1.get("Average")));
					prediction.setYcz2(String.valueOf(returnmap2.get("Average")));
					prediction.setYcz3(String.valueOf(returnmap3.get("Average")));
					prediction.setYcz4(String.valueOf(returnmap4.get("Average")));
					prediction.setYcz5(String.valueOf(returnmap5.get("Average")));
					prediction.setYcz6(String.valueOf(returnmap6.get("Average")));
					prediction.setYcz7(String.valueOf(returnmap7.get("Average")));
					prediction.setYcz8(String.valueOf(returnmap8.get("Average")));
					prediction.setYcz9(String.valueOf(returnmap9.get("Average")));
					prediction.setYcz10(String.valueOf(returnmap10.get("Average")));
					prediction.setYcz11(String.valueOf(returnmap11.get("Average")));
					prediction.setYcz12(String.valueOf(returnmap12.get("Average")));
					//标准差
					prediction.setBzc1(String.valueOf(returnmap1.get("result")));
					prediction.setBzc2(String.valueOf(returnmap2.get("result")));
					prediction.setBzc3(String.valueOf(returnmap3.get("result")));
					prediction.setBzc4(String.valueOf(returnmap4.get("result")));
					prediction.setBzc5(String.valueOf(returnmap5.get("result")));
					prediction.setBzc6(String.valueOf(returnmap6.get("result")));
					prediction.setBzc7(String.valueOf(returnmap7.get("result")));
					prediction.setBzc8(String.valueOf(returnmap8.get("result")));
					prediction.setBzc9(String.valueOf(returnmap9.get("result")));
					prediction.setBzc10(String.valueOf(returnmap10.get("result")));
					prediction.setBzc11(String.valueOf(returnmap11.get("result")));
					prediction.setBzc12(String.valueOf(returnmap12.get("result")));
					//真实值
					prediction.setZsz1(String.valueOf(returnmap1.get("real")));
					prediction.setZsz2(String.valueOf(returnmap2.get("real")));
					prediction.setZsz3(String.valueOf(returnmap3.get("real")));
					prediction.setZsz4(String.valueOf(returnmap4.get("real")));
					prediction.setZsz5(String.valueOf(returnmap5.get("real")));
					prediction.setZsz6(String.valueOf(returnmap6.get("real")));
					prediction.setZsz7(String.valueOf(returnmap7.get("real")));
					prediction.setZsz8(String.valueOf(returnmap8.get("real")));
					prediction.setZsz9(String.valueOf(returnmap9.get("real")));
					prediction.setZsz10(String.valueOf(returnmap10.get("real")));
					prediction.setZsz11(String.valueOf(returnmap11.get("real")));
					prediction.setZsz12(String.valueOf(returnmap12.get("real")));
					//方差
					prediction.setFx1(String.valueOf(returnmap1.get("fx")));
					prediction.setFx2(String.valueOf(returnmap2.get("fx")));
					prediction.setFx3(String.valueOf(returnmap3.get("fx")));
					prediction.setFx4(String.valueOf(returnmap4.get("fx")));
					prediction.setFx5(String.valueOf(returnmap5.get("fx")));
					prediction.setFx6(String.valueOf(returnmap6.get("fx")));
					prediction.setFx7(String.valueOf(returnmap7.get("fx")));
					prediction.setFx8(String.valueOf(returnmap8.get("fx")));
					prediction.setFx9(String.valueOf(returnmap9.get("fx")));
					prediction.setFx10(String.valueOf(returnmap10.get("fx")));
					prediction.setFx11(String.valueOf(returnmap11.get("fx")));
					prediction.setFx12(String.valueOf(returnmap12.get("fx")));
				}
				if(prediction.getYcff()==4) {
					Map returnmap1 = (Map)Standarddeviation4(prediction.getDataprocess_id(),1);
					Map returnmap2 = (Map)Standarddeviation4(prediction.getDataprocess_id(),2);
					Map returnmap3 = (Map)Standarddeviation4(prediction.getDataprocess_id(),3);
					Map returnmap4 = (Map)Standarddeviation4(prediction.getDataprocess_id(),4);
					Map returnmap5 = (Map)Standarddeviation4(prediction.getDataprocess_id(),5);
					Map returnmap6 = (Map)Standarddeviation4(prediction.getDataprocess_id(),6);
					Map returnmap7 = (Map)Standarddeviation4(prediction.getDataprocess_id(),7);
					Map returnmap8 = (Map)Standarddeviation4(prediction.getDataprocess_id(),8);
					Map returnmap9 = (Map)Standarddeviation4(prediction.getDataprocess_id(),9);
					Map returnmap10 = (Map)Standarddeviation4(prediction.getDataprocess_id(),10);
					Map returnmap11 = (Map)Standarddeviation4(prediction.getDataprocess_id(),11);
					Map returnmap12 = (Map)Standarddeviation4(prediction.getDataprocess_id(),12);
					//预测值
					prediction.setYcz1(String.valueOf(returnmap1.get("Average")));
					prediction.setYcz2(String.valueOf(returnmap2.get("Average")));
					prediction.setYcz3(String.valueOf(returnmap3.get("Average")));
					prediction.setYcz4(String.valueOf(returnmap4.get("Average")));
					prediction.setYcz5(String.valueOf(returnmap5.get("Average")));
					prediction.setYcz6(String.valueOf(returnmap6.get("Average")));
					prediction.setYcz7(String.valueOf(returnmap7.get("Average")));
					prediction.setYcz8(String.valueOf(returnmap8.get("Average")));
					prediction.setYcz9(String.valueOf(returnmap9.get("Average")));
					prediction.setYcz10(String.valueOf(returnmap10.get("Average")));
					prediction.setYcz11(String.valueOf(returnmap11.get("Average")));
					prediction.setYcz12(String.valueOf(returnmap12.get("Average")));
					//标准差
					prediction.setBzc1(String.valueOf(returnmap1.get("result")));
					prediction.setBzc2(String.valueOf(returnmap2.get("result")));
					prediction.setBzc3(String.valueOf(returnmap3.get("result")));
					prediction.setBzc4(String.valueOf(returnmap4.get("result")));
					prediction.setBzc5(String.valueOf(returnmap5.get("result")));
					prediction.setBzc6(String.valueOf(returnmap6.get("result")));
					prediction.setBzc7(String.valueOf(returnmap7.get("result")));
					prediction.setBzc8(String.valueOf(returnmap8.get("result")));
					prediction.setBzc9(String.valueOf(returnmap9.get("result")));
					prediction.setBzc10(String.valueOf(returnmap10.get("result")));
					prediction.setBzc11(String.valueOf(returnmap11.get("result")));
					prediction.setBzc12(String.valueOf(returnmap12.get("result")));
					//真实值
					prediction.setZsz1(String.valueOf(returnmap1.get("real")));
					prediction.setZsz2(String.valueOf(returnmap2.get("real")));
					prediction.setZsz3(String.valueOf(returnmap3.get("real")));
					prediction.setZsz4(String.valueOf(returnmap4.get("real")));
					prediction.setZsz5(String.valueOf(returnmap5.get("real")));
					prediction.setZsz6(String.valueOf(returnmap6.get("real")));
					prediction.setZsz7(String.valueOf(returnmap7.get("real")));
					prediction.setZsz8(String.valueOf(returnmap8.get("real")));
					prediction.setZsz9(String.valueOf(returnmap9.get("real")));
					prediction.setZsz10(String.valueOf(returnmap10.get("real")));
					prediction.setZsz11(String.valueOf(returnmap11.get("real")));
					prediction.setZsz12(String.valueOf(returnmap12.get("real")));
					//方差
					prediction.setFx1(String.valueOf(returnmap1.get("fx")));
					prediction.setFx2(String.valueOf(returnmap2.get("fx")));
					prediction.setFx3(String.valueOf(returnmap3.get("fx")));
					prediction.setFx4(String.valueOf(returnmap4.get("fx")));
					prediction.setFx5(String.valueOf(returnmap5.get("fx")));
					prediction.setFx6(String.valueOf(returnmap6.get("fx")));
					prediction.setFx7(String.valueOf(returnmap7.get("fx")));
					prediction.setFx8(String.valueOf(returnmap8.get("fx")));
					prediction.setFx9(String.valueOf(returnmap9.get("fx")));
					prediction.setFx10(String.valueOf(returnmap10.get("fx")));
					prediction.setFx11(String.valueOf(returnmap11.get("fx")));
					prediction.setFx12(String.valueOf(returnmap12.get("fx")));
				}
				dt_predictionservice.saveOrUpdate(prediction);
				
				System.out.println("==="+i);
			}
			adminsharedpartstatistics();
			json.put("code", "100");
			json.put("info", "统计成功，请刷新页面");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}
	//共用件优化统计
	private void adminsharedpartstatistics() throws IOException {
		DecimalFormat df = new DecimalFormat("#");
		dt_sharedpart sharedpart2 = dt_sharedpartservice.get(1);
		List list = dt_sharedpartservice.adminfindsharedpartlistall();
		for(int i=0;i<list.size();i++){
			Map map = (Map)list.get(i);
			dt_sharedpart sharedpart = dt_sharedpartservice.get(Integer.valueOf(String.valueOf(map.get("sharedpart_id"))));
			
			double predictionnumber = Double.valueOf(String.valueOf(map.get("ycz11")));
//			double bzc = Double.valueOf(String.valueOf(map.get("bzc11")));
//			double fx = Double.valueOf(String.valueOf(map.get("fx11")));
			
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
//			sharedpart.setPlannumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9*(sharedpart2.getKyd()*2-1))));
			sharedpart.setMaxnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,1)))));
			sharedpart.setPlannumber(Integer.valueOf(String.valueOf(df.format(percentile(data,sharedpart2.getKyd())))));
			sharedpart.setRealnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,sharedpart2.getKyd())))));
			dt_sharedpartservice.saveOrUpdate(sharedpart);
			System.out.println("共用件优化："+i);
		}
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
	//标准差计算--移动平均法
	private JSONObject Standarddeviation(Integer dataprocess_id,Integer agenumbner) throws IOException {
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
//		Double Average=(double) 0;
//		Double Average1=(double) 0;
//		Double Average2=(double) 0;
//		Double Average3=(double) 0;
//		Double Average4=(double) 0;
//		Double Average5=(double) 0;
//		Double Average6=(double) 0;
//		Double Average7=(double) 0;
//		Double Average8=(double) 0;
//		Double Average9=(double) 0;
//		Double Average10=(double) 0;
//		Double Average11=(double) 0;//年份11预测充当真实值
//		Double Average12=(double) 0;
		
		Double result=(double) 0;
		Double real=(double) 0;
		Double fx=(double) 0;
		dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
		
		Double Average=(double) 0;
		Double Average1=dataprocess.getAge1();
		Double Average2=dataprocess.getAge2();
		Double Average3=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()
				)/2));
		Double Average4=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()
				)/3));
		Double Average5=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				)/4));
		Double Average6=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()
				)/5));
		Double Average7=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()
				)/6));
		Double Average8=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()+dataprocess.getAge7()
				)/7));
		Double Average9=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()+dataprocess.getAge7()
				+dataprocess.getAge8()
				)/8));
		Double Average10=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()+dataprocess.getAge7()
				+dataprocess.getAge8()+dataprocess.getAge9()
				)/9));
		Double Average11=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()+dataprocess.getAge7()
				+dataprocess.getAge8()+dataprocess.getAge9()+dataprocess.getAge10()
				)/10));//年份11预测充当真实值
		Double Average12=Double.valueOf(df.format((double)(dataprocess.getAge1()
				+dataprocess.getAge2()+dataprocess.getAge3()+dataprocess.getAge4()
				+dataprocess.getAge5()+dataprocess.getAge6()+dataprocess.getAge7()
				+dataprocess.getAge8()+dataprocess.getAge9()+dataprocess.getAge10()
				+Average11
				)/11));
		
		if(agenumbner==1) {
			Average = Average1;//Double.valueOf(df.format((double)(dataprocess.getAge1())/1));
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge1());
			fx = number;
		}else if(agenumbner==2) {
			Average = Average2;//Double.valueOf(df.format((double)(dataprocess.getAge1())/1)); 
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge2());
			fx = number;
		}else if(agenumbner==3) {
			Average = Average3; 
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					)/2;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge3());
			fx = number;
		}else if(agenumbner==4) {
			Average = Average4;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					)/3;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge4());
			fx = number;
		}else if(agenumbner==5) {
			Average = Average5;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					)/4;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge5());
			fx = number;
		}else if(agenumbner==6) {
			Average = Average6;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					)/5;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge6());
			fx = number;
		}else if(agenumbner==7) {
			Average = Average7;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					)/6;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge7());
			fx = number;
		}else if(agenumbner==8) {
			Average = Average8;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					)/7;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge8());
			fx = number;
		}else if(agenumbner==9) {
			Average = Average9;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					)/8;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge9());
			fx = number;
		}else if(agenumbner==10) {
			Average = Average10;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-Average9),2)
					)/9;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge10());
			fx = number;
		}else if(agenumbner==11) {
			Average = Average11;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-Average9),2)
					+Math.pow((Double.valueOf(dataprocess.getAge10())-Average10),2)
					)/10;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}else if(agenumbner==12) {
			Average = Average12;
			Double number = (double) 0;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}
		json.put("Average", Average);
		json.put("result", result);
		json.put("real", real);
		json.put("fx", Double.valueOf(df.format(fx)));
		return json;
	}
	//标准差计算--指数平滑法
	private JSONObject Standarddeviation2(Integer dataprocess_id,Integer agenumbner) throws IOException {
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
		Double Average=(double) 0;
//		Double Average11=(double) 0;//年份11预测充当真实值
		Double result=(double) 0;
		Double real=(double) 0;
		Double fx=(double) 0;
		dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
		//预测值
		Double average1 = (double) dataprocess.getAge1();
		Double average2 = (double) dataprocess.getAge2();//dataprocess.getAnumber()*dataprocess.getAge1()+(1-dataprocess.getAnumber())*average1;
		Double average3 = dataprocess.getAnumber()*dataprocess.getAge2()+(1-dataprocess.getAnumber())*average2;
		Double average4 = dataprocess.getAnumber()*dataprocess.getAge3()+(1-dataprocess.getAnumber())*average3;
		Double average5 = dataprocess.getAnumber()*dataprocess.getAge4()+(1-dataprocess.getAnumber())*average4;
		Double average6 = dataprocess.getAnumber()*dataprocess.getAge5()+(1-dataprocess.getAnumber())*average5;
		Double average7 = dataprocess.getAnumber()*dataprocess.getAge6()+(1-dataprocess.getAnumber())*average6;
		Double average8 = dataprocess.getAnumber()*dataprocess.getAge7()+(1-dataprocess.getAnumber())*average7;
		Double average9 = dataprocess.getAnumber()*dataprocess.getAge8()+(1-dataprocess.getAnumber())*average8;
		Double average10 = dataprocess.getAnumber()*dataprocess.getAge9()+(1-dataprocess.getAnumber())*average9;
		Double average11 = dataprocess.getAnumber()*dataprocess.getAge10()+(1-dataprocess.getAnumber())*average10;
		Double average12 = dataprocess.getAnumber()*average11+(1-dataprocess.getAnumber())*average11;
		
		if(agenumbner==1) {
			Average = Double.valueOf(df.format(average1));
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge1());
			fx = number;
		}else if(agenumbner==2) {
			Average = Double.valueOf(df.format(average2));
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge2());
			fx = number;
		}else if(agenumbner==3) {
			Average = Double.valueOf(df.format(average3)); 
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					)/2;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge3());
			fx = number;
		}else if(agenumbner==4) {
			Average = Double.valueOf(df.format(average4));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					)/3;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge4());
			fx = number;
		}else if(agenumbner==5) {
			Average = Double.valueOf(df.format(average5));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					)/4;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge5());
			fx = number;
		}else if(agenumbner==6) {
			Average = Double.valueOf(df.format(average6));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					)/5;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge6());
			fx = number;
		}else if(agenumbner==7) {
			Average = Double.valueOf(df.format(average7));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					)/6;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge7());
			fx = number;
		}else if(agenumbner==8) {
			Average = Double.valueOf(df.format(average8));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					)/7;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge8());
			fx = number;
		}else if(agenumbner==9) {
			Average = Double.valueOf(df.format(average9));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					)/8;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge9());
			fx = number;
		}else if(agenumbner==10) {
			Average = Double.valueOf(df.format(average10));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-average9),2)
					)/9;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge10());
			fx = number;
		}else if(agenumbner==11) {
			Average = Double.valueOf(df.format(average11));
//			Average11 = Average;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-average9),2)
					+Math.pow((Double.valueOf(dataprocess.getAge10())-average10),2)
					)/10;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}else if(agenumbner==12) {
			Average = Double.valueOf(df.format(average12));
			Double number = (double) 0;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}
		json.put("Average", Average);
		json.put("result", result);
		json.put("real", real);
		json.put("fx", Double.valueOf(df.format(fx)));
		return json;
	}	
//	public static double[] zsph(double[] data, double a){
//		double[] yc;
//		int n=data.length;  
//		if (n==1||n==2){
//			return [data[n],data[n]];
//		}else if(n>2){
//			yc[1]=data[1];
//			yc[2]=data[2];
//			for(int i=2;i<=n;i++){
//				yc[i+1]=data[i]*a+yc[i]*(1-a);
//			}
//			yc[n+2]=yc[n+1];
//			return [yc[n+1],yc[n+2]];
//		}
//	}
	//标准差计算--线性回归法
	private JSONObject Standarddeviation3(Integer dataprocess_id,Integer agenumbner) throws IOException {
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
		Double Average=(double) 0;
//		Double Average11=(double) 0;//年份11预测充当真实值
		Double result=(double) 0;
		Double real=(double) 0;
		Double fx=(double) 0;
		dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
		//预测值
		Double average1 = (double) dataprocess.getAge1();
		Double average2 = (double) dataprocess.getAge2();
		
		double[] data3 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2()};
		double[] data4 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3()};
		double[] data5 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4()};
		double[] data6 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5()};
		double[] data7 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6()};
		double[] data8 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6(),(double) dataprocess.getAge7()};
		double[] data9 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6(),(double) dataprocess.getAge7(),(double) dataprocess.getAge8()};
		double[] data10 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6(),(double) dataprocess.getAge7(),(double) dataprocess.getAge8(),(double) dataprocess.getAge9()};
		double[] data11 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6(),(double) dataprocess.getAge7(),(double) dataprocess.getAge8(),(double) dataprocess.getAge9(),(double) dataprocess.getAge10()};
		
		System.out.println("============="+percentile3(data3));
		
		
		
		Double average3 = (double) 0;
		if(String.valueOf(percentile3(data3)).equals("NaN")) {
			average3=(double) 0;
		}else {
			average3 = percentile3(data3);
		}
		Double average4 = (double) 0;
		if(String.valueOf(percentile3(data4)).equals("NaN")) {
			average4=(double) 0;
		}else {
			average4 = percentile3(data4);
		}
		Double average5 = (double) 0;
		if(String.valueOf(percentile3(data5)).equals("NaN")) {
			average5=(double) 0;
		}else {
			average5 = percentile3(data5);
		}
		Double average6 = (double) 0;
		if(String.valueOf(percentile3(data6)).equals("NaN")) {
			average6=(double) 0;
		}else {
			average6 = percentile3(data6);
		}
		Double average7 = (double) 0;
		if(String.valueOf(percentile3(data7)).equals("NaN")) {
			average7=(double) 0;
		}else {
			average7 = percentile3(data7);
		}
		Double average8 = (double) 0;
		if(String.valueOf(percentile3(data8)).equals("NaN")) {
			average8=(double) 0;
		}else {
			average8 = percentile3(data8);
		}
		Double average9 = (double) 0;
		if(String.valueOf(percentile3(data9)).equals("NaN")) {
			average9=(double) 0;
		}else {
			average9 = percentile3(data9);
		}
		Double average10 = (double) 0;
		if(String.valueOf(percentile3(data10)).equals("NaN")) {
			average10=(double) 0;
		}else {
			average10 = percentile3(data10);
		}
		Double average11 = (double) 0;
		if(String.valueOf(percentile3(data11)).equals("NaN")) {
			average11=(double) 0;
		}else {
			average11 = percentile3(data11);
		}
		
//		Double average3 = percentile3(data3);
//		Double average4 = percentile3(data4);
//		Double average5 = percentile3(data5);
//		Double average6 = percentile3(data6);
//		Double average7 = percentile3(data7);
//		Double average8 = percentile3(data8);
//		Double average9 = percentile3(data9);
//		Double average10 = percentile3(data10);
//		Double average11 = percentile3(data11);
		
		double[] data12 = {(double) dataprocess.getAge1(),(double) dataprocess.getAge2(),(double) dataprocess.getAge3(),(double) dataprocess.getAge4(),(double) dataprocess.getAge5(),(double) dataprocess.getAge6(),(double) dataprocess.getAge7(),(double) dataprocess.getAge8(),(double) dataprocess.getAge9(),(double) dataprocess.getAge10(),average11};
//		Double average12 = percentile3(data12);
		Double average12 = (double) 0;
		if(String.valueOf(percentile3(data12)).equals("NaN")) {
			average12=(double) 0;
		}else {
			average12 = percentile3(data12);
		}
		
		if(agenumbner==1) {
			Average = Double.valueOf(df.format(average1));
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge1());
			fx = number;
		}else if(agenumbner==2) {
			Average = Double.valueOf(df.format(average2));
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge2());
			fx = number;
		}else if(agenumbner==3) {
			Average = Double.valueOf(df.format(average3)); 
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					)/2;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge3());
			fx = number;
		}else if(agenumbner==4) {
			Average = Double.valueOf(df.format(average4));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					)/3;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge4());
			fx = number;
		}else if(agenumbner==5) {
			Average = Double.valueOf(df.format(average5));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					)/4;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge5());
			fx = number;
		}else if(agenumbner==6) {
			Average = Double.valueOf(df.format(average6));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					)/5;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge6());
			fx = number;
		}else if(agenumbner==7) {
			Average = Double.valueOf(df.format(average7));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					)/6;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge7());
			fx = number;
		}else if(agenumbner==8) {
			Average = Double.valueOf(df.format(average8));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					)/7;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge8());
			fx = number;
		}else if(agenumbner==9) {
			Average = Double.valueOf(df.format(average9));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					)/8;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge9());
			fx = number;
		}else if(agenumbner==10) {
			Average = Double.valueOf(df.format(average10));
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-average9),2)
					)/9;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge10());
			fx = number;
		}else if(agenumbner==11) {
			Average = Double.valueOf(df.format(average11));
//			Average11 = Average;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-average9),2)
					+Math.pow((Double.valueOf(dataprocess.getAge10())-average10),2)
					)/10;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}else if(agenumbner==12) {
			Average = Double.valueOf(df.format(average12));
			Double number = (double) 0;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}
		
		json.put("Average", Average);
		json.put("result", result);
		json.put("real", real);
		json.put("fx", Double.valueOf(df.format(fx)));
		return json;
	}
	public static double percentile3(double[] data){ 
		int n=data.length;
		int i=0;
		//计算b的分子
		double a=0;
		double b=0;
		double xb=0;
		double yb=0;
		for(i=0;i<n;i++){
			xb=i+xb;
		}
		xb=xb/n;
		for(i=0;i<n;i++)
		{
			yb=data[i]+yb;
		}
		yb=yb/n;
		double bs=0;
		double bh=0;
		for(i=0;i<n;i++){
			bs=bs+(i-xb)*(data[i]-yb);
			bh=(i-xb)*(i-xb)+bh;
		}
		b=bs/bh;
		a=yb-b*xb;
		return  a+b*n;
	}
	//标准差计算--组合预测
	private JSONObject Standarddeviation4(Integer dataprocess_id,Integer agenumbner) throws IOException {
		JSONObject json = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
//		Double Average=(double) 0;
//		Double Average1=(double) 0;
//		Double Average2=(double) 0;
//		Double Average3=(double) 0;
//		Double Average4=(double) 0;
//		Double Average5=(double) 0;
//		Double Average6=(double) 0;
//		Double Average7=(double) 0;
//		Double Average8=(double) 0;
//		Double Average9=(double) 0;
//		Double Average10=(double) 0;
//		Double Average11=(double) 0;//年份11预测充当真实值
//		Double Average12=(double) 0;
		Double result=(double) 0;
		Double real=(double) 0;
		Double fx=(double) 0;
		
		
		List predictionlist1 = dt_predictionservice.adminfindpredictionfordidandtype(dataprocess_id, 1);
		List predictionlist2 = dt_predictionservice.adminfindpredictionfordidandtype(dataprocess_id, 2);
		List predictionlist3 = dt_predictionservice.adminfindpredictionfordidandtype(dataprocess_id, 3);
		
		
		dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
		
		Double Average=(double) 0;
		Double Average1=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc1")));
				b1=Double.valueOf(String.valueOf(map.get("ycz1")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc1")));
				b2=Double.valueOf(String.valueOf(map.get("ycz1")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc1")));
				b3=Double.valueOf(String.valueOf(map.get("ycz1")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average1 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average2=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc2")));
				b1=Double.valueOf(String.valueOf(map.get("ycz2")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc2")));
				b2=Double.valueOf(String.valueOf(map.get("ycz2")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc2")));
				b3=Double.valueOf(String.valueOf(map.get("ycz2")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average2 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average3=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc3")));
				b1=Double.valueOf(String.valueOf(map.get("ycz3")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc3")));
				b2=Double.valueOf(String.valueOf(map.get("ycz3")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc3")));
				b3=Double.valueOf(String.valueOf(map.get("ycz3")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average3 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average4=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc4")));
				b1=Double.valueOf(String.valueOf(map.get("ycz4")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc4")));
				b2=Double.valueOf(String.valueOf(map.get("ycz4")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc4")));
				b3=Double.valueOf(String.valueOf(map.get("ycz4")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average4 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average5=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc5")));
				b1=Double.valueOf(String.valueOf(map.get("ycz5")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc5")));
				b2=Double.valueOf(String.valueOf(map.get("ycz5")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc5")));
				b3=Double.valueOf(String.valueOf(map.get("ycz5")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average5 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average6=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc6")));
				b1=Double.valueOf(String.valueOf(map.get("ycz6")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc6")));
				b2=Double.valueOf(String.valueOf(map.get("ycz6")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc6")));
				b3=Double.valueOf(String.valueOf(map.get("ycz6")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average6 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average7=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc7")));
				b1=Double.valueOf(String.valueOf(map.get("ycz7")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc7")));
				b2=Double.valueOf(String.valueOf(map.get("ycz7")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc7")));
				b3=Double.valueOf(String.valueOf(map.get("ycz7")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average7 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average8=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc8")));
				b1=Double.valueOf(String.valueOf(map.get("ycz8")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc8")));
				b2=Double.valueOf(String.valueOf(map.get("ycz8")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc8")));
				b3=Double.valueOf(String.valueOf(map.get("ycz8")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average8 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average9=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc9")));
				b1=Double.valueOf(String.valueOf(map.get("ycz9")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc9")));
				b2=Double.valueOf(String.valueOf(map.get("ycz9")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc9")));
				b3=Double.valueOf(String.valueOf(map.get("ycz9")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average9 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average10=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc10")));
				b1=Double.valueOf(String.valueOf(map.get("ycz10")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc10")));
				b2=Double.valueOf(String.valueOf(map.get("ycz10")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc10")));
				b3=Double.valueOf(String.valueOf(map.get("ycz10")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average10 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average11=(double) 0;//年份11预测充当真实值
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc11")));
				b1=Double.valueOf(String.valueOf(map.get("ycz11")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc11")));
				b2=Double.valueOf(String.valueOf(map.get("ycz11")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc11")));
				b3=Double.valueOf(String.valueOf(map.get("ycz11")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average11 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		Double Average12=(double) 0;
		if(true) {
			Double a1 = (double) 0;
			Double a2 = (double) 0;
			Double a3 = (double) 0;
			Double b1 = (double) 0;
			Double b2 = (double) 0;
			Double b3 = (double) 0;
			
			if(predictionlist1.size()>0) {
				Map map = (Map)predictionlist1.get(0);
				a1=Double.valueOf(String.valueOf(map.get("bzc12")));
				b1=Double.valueOf(String.valueOf(map.get("ycz12")));
			}
			if(predictionlist2.size()>0) {
				Map map = (Map)predictionlist2.get(0);
				a2=Double.valueOf(String.valueOf(map.get("bzc12")));
				b2=Double.valueOf(String.valueOf(map.get("ycz12")));
			}
			if(predictionlist3.size()>0) {
				Map map = (Map)predictionlist3.get(0);
				a3=Double.valueOf(String.valueOf(map.get("bzc12")));
				b3=Double.valueOf(String.valueOf(map.get("ycz12")));
			}
			
			double[] bzc = {a1,a2,a3};
			double[] ycz = {b1,b2,b3};
			
			Average12 = Double.valueOf(df.format(zhyc(ycz,bzc)));
		}
		
		if(agenumbner==1) {
			Average = Average1;
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge1());
			fx = number;
		}else if(agenumbner==2) {
			Average = Average2;
			Double number = (double) 0;//(Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2))/1;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge2());
			fx = number;
		}else if(agenumbner==3) {
			Average = Average3;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					)/2;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge3());
			fx = number;
		}else if(agenumbner==4) {
			Average = Average4;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					)/3;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge4());
			fx = number;
		}else if(agenumbner==5) {
			Average = Average5;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					)/4;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge5());
			fx = number;
		}else if(agenumbner==6) {
			Average = Average6;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					)/5;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge6());
			fx = number;
		}else if(agenumbner==7) {
			Average = Average7;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					)/6;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge7());
			fx = number;
		}else if(agenumbner==8) {
			Average = Average8;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					)/7;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge8());
			fx = number;
		}else if(agenumbner==9) {
			Average = Average9;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					)/8;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge9());
			fx = number;
		}else if(agenumbner==10) {
			Average = Average10;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-Average9),2)
					)/9;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(dataprocess.getAge10());
			fx = number;
		}else if(agenumbner==11) {
			Average = Average11;
			Double number = (Math.pow((Double.valueOf(dataprocess.getAge1())-Average1),2)
					+Math.pow((Double.valueOf(dataprocess.getAge2())-Average2),2)
					+Math.pow((Double.valueOf(dataprocess.getAge3())-Average3),2)
					+Math.pow((Double.valueOf(dataprocess.getAge4())-Average4),2)
					+Math.pow((Double.valueOf(dataprocess.getAge5())-Average5),2)
					+Math.pow((Double.valueOf(dataprocess.getAge6())-Average6),2)
					+Math.pow((Double.valueOf(dataprocess.getAge7())-Average7),2)
					+Math.pow((Double.valueOf(dataprocess.getAge8())-Average8),2)
					+Math.pow((Double.valueOf(dataprocess.getAge9())-Average9),2)
					+Math.pow((Double.valueOf(dataprocess.getAge10())-Average10),2)
					)/10;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}else if(agenumbner==12) {
			Average = Average12;
			Double number = (double) 0;
			result = Double.valueOf(df.format(Math.sqrt(number)));
			real = Double.valueOf(0);
			fx = number;
		}
		
		json.put("Average", Average);
		json.put("result", result);
		json.put("real", real);
		json.put("fx", Double.valueOf(df.format(fx)));
		return json;
	}
	public static double zhyc(double[] yc,double[] bzc){
		int n=yc.length;
		double x=bzc[0];
		double fm=0;
		double fm1=0;
		double y=0;
		for (int i=0;i<n;i++){
			if(bzc[i]==0){
				return yc[i];
			}else{
				fm=(1/bzc[i])+fm;
			}	
		}
		for(int i=0;i<n;i++){
			fm1=(1/bzc[i])/fm;
			y=y+fm1*yc[i];
		}
		return y;
	}
	//Excel生成
	@RequestMapping(value = "/adminpredictionExcel",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminpredictionExcel(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			//生成xls表头
			List<String> header = new ArrayList<String>(); // 第一行数据
			List<List<String>> body = new ArrayList<List<String>>();
			header.add("军区编号");
		    header.add("部队编号");
			header.add("装备代码");
		    header.add("器材代码");
		    header.add("器材名称");
		    header.add("预测方法");
		    header.add("预测值(去年消耗)");
		    header.add("方差(去年消耗)");
		    header.add("真实值(去年消耗)");
		    header.add("预测值(今年消耗)");
		    header.add("方差(今年消耗)");
		    header.add("真实值(今年消耗)");
		    header.add("预测值(明年消耗)");
		    header.add("预测值(后年消耗)");
		    
		    List items = dt_predictionservice.adminfindpredictionlist();
			for(int i=0;i<items.size();i++){
				Map map = (Map)items.get(i);
				if(Integer.valueOf(String.valueOf(map.get("ycff")))==1) {
					map.put("ycffname", "简单移动平均法");
				}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==2) {
					map.put("ycffname", "指数平滑法");
				}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==3) {
					map.put("ycffname", "线性回归法");
				}else if(Integer.valueOf(String.valueOf(map.get("ycff")))==4) {
					map.put("ycffname", "误差平方和倒数组合预测");
				}
				//添加xls信息
				List<String> data = new ArrayList<String>();
				data.add(String.valueOf(map.get("jqcode")));
				data.add(String.valueOf(map.get("bdcode")));
				data.add(String.valueOf(map.get("zbcode")));
				data.add(String.valueOf(map.get("qccode")));
				data.add(String.valueOf(map.get("qcname")));
				data.add(String.valueOf(map.get("ycffname")));
				data.add(String.valueOf(map.get("ycz9")));
				data.add(String.valueOf(map.get("fx9")));
				data.add(String.valueOf(map.get("zsz9")));
				data.add(String.valueOf(map.get("ycz10")));
				data.add(String.valueOf(map.get("fx10")));
				data.add(String.valueOf(map.get("zsz10")));
				data.add(String.valueOf(map.get("ycz11")));
				data.add(String.valueOf(map.get("ycz12")));
		    	body.add(data);
		    	
			}
			//xls输出
			String loadpath = request.getSession().getServletContext().getRealPath("/") + "..//upload" + File.separator;
		    //新建文件路径
		    File file2 = new File(loadpath);
			if (!file2.exists()) {
				file2.mkdir();
			}
			try(OutputStream out = new FileOutputStream(loadpath+"/"+"预测查询.xls")){
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
	//预测方法选择
	@RequestMapping("/adminpredictionycffedit")
	public String adminpredictionycffedit(HttpServletRequest request, HttpServletResponse resp,Integer dataprocess_id) 
			throws IOException {
		dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
		request.setAttribute("dataprocess", dataprocess);
		return "admin/predictionycffedit";
	}
	@RequestMapping(value = "/adminpredictionycffeditajax",produces = "application/json; charset=utf-8")
	@ResponseBody
	private String adminpredictionycffeditajax(HttpServletRequest request,HttpServletResponse resp,
			Integer dataprocess_id,Integer ycff,Float anumber) throws IOException {
		HttpSession session = request.getSession();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONObject json = new JSONObject();
		try{
			dt_dataprocess dataprocess = dt_dataprocessservice.get(dataprocess_id);
			dataprocess.setYcff(ycff);
			dataprocess.setAnumber(anumber);
			dt_dataprocessservice.saveOrUpdate(dataprocess);
			
			adminsharedpartsinglestatistics(dataprocess_id);
			json.put("code", "100");
			json.put("info", "更改成功");
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", "400");
			json.put("info", "系统错误");
		}
		return json.toString();
	}
	//更新单个共用件优化统计
		private void adminsharedpartsinglestatistics(Integer dataprocess_id) throws IOException {
			DecimalFormat df = new DecimalFormat("#");
			dt_sharedpart sharedpart2 = dt_sharedpartservice.get(1);
			List list = dt_sharedpartservice.adminfindsharedpartlistall();
			for(int i=0;i<list.size();i++){
				//if(i==dataprocess_id){
				Map map = (Map)list.get(i);
				dt_sharedpart sharedpart = dt_sharedpartservice.get(Integer.valueOf(String.valueOf(map.get("sharedpart_id"))));
					double predictionnumber = Double.valueOf(String.valueOf(map.get("ycz11")));
//					double bzc = Double.valueOf(String.valueOf(map.get("bzc11")));
//					double fx = Double.valueOf(String.valueOf(map.get("fx11")));
					
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
//					sharedpart.setMaxnumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9)));
//					sharedpart.setPlannumber(Integer.valueOf(df.format(predictionnumber))+Integer.valueOf(df.format(bzc*3.9*(sharedpart2.getKyd()*2-1))));
					sharedpart.setMaxnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,1)))));
					sharedpart.setPlannumber(Integer.valueOf(String.valueOf(df.format(percentile(data,sharedpart2.getKyd())))));
					sharedpart.setRealnumber(Integer.valueOf(String.valueOf(df.format(percentile(data,sharedpart2.getKyd())))));
					dt_sharedpartservice.saveOrUpdate(sharedpart);
					System.out.println("共用件优化："+sharedpart);
				//}
			}
		}
}






