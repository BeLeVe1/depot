package com.hd.microblog.service.impl;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_outDao;
import com.hd.microblog.model.fd_out;
import com.hd.microblog.service.fd_outService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fd_outService")
public class fd_outServiceImpl extends BaseService<fd_out, Integer> implements fd_outService{
	private fd_outDao fd_outdao;
	@Autowired
	@Qualifier("fd_outDao")
	@Override
	public void setBaseDao(IBaseDao<fd_out, Integer> fd_outdao) {
		this.baseDao = fd_outdao;
		this.fd_outdao = (fd_outDao) fd_outdao;
	}
	@Override
	public List adminfinddataprocessslist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_out where 1=1 ";
		return fd_outdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfinddataprocesslist(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg, String sort,
			Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_out where 1=1 ";
		if(jqcode!=""){
			sql+=" and jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and fg=? ";
			paramlist.add(fg);
		}
		if(sort!=""){
			sql+=" order by "+sort+" desc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return fd_outdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocesslistcount(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_out where 1=1 ";
		if(jqcode!=""){
			sql+=" and jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and fg=? ";
			paramlist.add(fg);
		}
		return fd_outdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocessgroup(String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "SELECT d.*,SUM(d.unitprice*s.plannumber) zbmoney FROM fd_out d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where d.fg=? GROUP BY d.zbcode ";
		paramlist.add(fg);
		return fd_outdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocesslistforqccode(String qccode) {
		// TODO Auto-generated method stub
		String sql = "From fd_out where qccode="+qccode;
		return fd_outdao.exehqlrelist(sql, null);
	}
}
