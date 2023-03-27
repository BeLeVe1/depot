package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_schedulemanaDao;
import com.hd.microblog.model.dt_schedulemana;
import com.hd.microblog.service.dt_schedulemanaService;
@Service("dt_schedulemanaService")
public class dt_schedulemanaServiceImpl extends BaseService<dt_schedulemana, Integer> implements dt_schedulemanaService{
	private dt_schedulemanaDao dt_schedulemanadao;
	@Autowired
	@Qualifier("dt_schedulemanaDao")
	@Override
	public void setBaseDao(IBaseDao<dt_schedulemana, Integer> dt_schedulemanadao) {
		this.baseDao = dt_schedulemanadao;
		this.dt_schedulemanadao = (dt_schedulemanaDao) dt_schedulemanadao;
	}
	@Override
	public List adminfindschedulemanalist(String schedule_number,String orcode, String gjcode, String opcode, Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_schedulemana where 1=1 ";
		if(schedule_number!=""){
			sql+=" and schedule_number=? ";
			paramlist.add(schedule_number);
		}
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(opcode!=""){
			sql+=" and opcode=? ";
			paramlist.add(opcode);
		}
		sql += " group by schedule_number";
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return dt_schedulemanadao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindschedulemanalistcount(String schedule_number,String orcode, String gjcode, String opcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(distinct schedule_number) from dt_schedulemana where 1=1 ";
		if(schedule_number!=""){
			sql+=" and schedule_number=? ";
			paramlist.add(schedule_number);
		}
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(opcode!=""){
			sql+=" and opcode=? ";
			paramlist.add(opcode);
		}
//		sql += " group by schedule_number";
		return dt_schedulemanadao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindschedulemanadetaillist(String schedule_number,String orcode, String gjcode, String opcode, Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_schedulemana where 1=1 ";
		System.out.println(schedule_number);
		if(schedule_number!=""){
			sql+=" and schedule_number=? ";
			paramlist.add(schedule_number);
		}
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(opcode!=""){
			sql+=" and opcode=? ";
			paramlist.add(opcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return dt_schedulemanadao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindschedulemanadetaillistcount(String schedule_number,String orcode, String gjcode, String opcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_schedulemana where 1=1 ";
		if(schedule_number!=""){
			sql+=" and schedule_number=? ";
			paramlist.add(schedule_number);
		}
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(opcode!=""){
			sql+=" and opcode=? ";
			paramlist.add(opcode);
		}
		return dt_schedulemanadao.exesqlrelist(sql, paramlist);
	}

}
