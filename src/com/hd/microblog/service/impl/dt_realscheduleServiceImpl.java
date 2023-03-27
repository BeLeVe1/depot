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
import com.hd.microblog.dao.dt_realscheduleDao;
import com.hd.microblog.model.dt_realschedule;
import com.hd.microblog.service.dt_realscheduleService;
@Service("dt_realscheduleService")
public class dt_realscheduleServiceImpl extends BaseService<dt_realschedule, Integer> implements dt_realscheduleService{
	private dt_realscheduleDao dt_realscheduledao;
	@Autowired
	@Qualifier("dt_realscheduleDao")
	@Override
	public void setBaseDao(IBaseDao<dt_realschedule, Integer> dt_realscheduledao) {
		this.baseDao = dt_realscheduledao;
		this.dt_realscheduledao = (dt_realscheduleDao) dt_realscheduledao;
	}
	@Override
	public List adminfindrealschedulelist(String orcode, String gjcode, String opcode, Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_realschedule where 1=1 ";
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
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindrealschedulelistcount(String orcode, String gjcode, String opcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_realschedule where 1=1 ";
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
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindcpname(String cpcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select cpname from dt_dataprocess where 1=1 ";
		if(cpcode!=""){
			sql+=" and cpcode=? ";
			paramlist.add(cpcode);
		}
		System.out.println(paramlist);
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindgjname(String gjcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select gjname from dt_dataprocess where 1=1 ";
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		System.out.println(paramlist);
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindopname(String opcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select opname from dt_dataprocess where 1=1 ";
		if(opcode!=""){
			sql+=" and opcode=? ";
			paramlist.add(opcode);
		}
		System.out.println(paramlist);
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindjqname(String jqcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select machine_name from dt_machine_info where 1=1 ";
		if(jqcode!=""){
			sql+=" and machine_id=? ";
			paramlist.add(jqcode);
		}
		System.out.println(paramlist);
		return dt_realscheduledao.exesqlrelist(sql, paramlist);
	}

}
