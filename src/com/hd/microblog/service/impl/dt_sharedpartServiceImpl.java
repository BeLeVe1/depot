package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_sharedpartDao;
import com.hd.microblog.model.dt_sharedpart;
import com.hd.microblog.service.dt_sharedpartService;
@Service("dt_sharedpartService")
public class dt_sharedpartServiceImpl extends BaseService<dt_sharedpart, Integer> implements dt_sharedpartService{
	private dt_sharedpartDao dt_sharedpartdao;
	@Autowired
	@Qualifier("dt_sharedpartDao")
	@Override
	public void setBaseDao(IBaseDao<dt_sharedpart, Integer> dt_sharedpartdao) {
		this.baseDao = dt_sharedpartdao;
		this.dt_sharedpartdao = (dt_sharedpartDao) dt_sharedpartdao;
	}
	@Override
	public List adminfindsharedpartlist(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg,String sort,Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select d.*,s.sharedpart_id,s.kyd,s.predictionnumber,s.maxnumber,s.plannumber from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where 1=1 ";
		if(jqcode!=""){
			sql+=" and d.jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and d.bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and d.zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and d.qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and d.qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and d.fg=? ";
			paramlist.add(fg);
		}
		if(sort!=""){
			sql+=" order by s."+sort+" desc ";
		}else {
			sql+=" order by d.qccode asc,d.jqcode asc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartlistcount(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where 1=1 ";
		if(jqcode!=""){
			sql+=" and jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and bdcode=? ";
			paramlist.add(bdcode);
		}
		if(qcname!=""){
			sql+=" and qcname=? ";
			paramlist.add(qcname);
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
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartlist(String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select d.*,s.sharedpart_id,s.kyd,s.predictionnumber,s.plannumber,s.maxnumber from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where d.fg=? order by d.qccode asc,d.jqcode asc ";
		paramlist.add(fg);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartfordataprocess_id(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "From dt_sharedpart where dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return dt_sharedpartdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartlistall() {
		// TODO Auto-generated method stub
		String sql = "select sd.sharedpart_id,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff ";
		return dt_sharedpartdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindsharedpartlistforfg(String fg) {
		// TODO Auto-generated method stub
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and dp.fg='"+fg+"'";
		return dt_sharedpartdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindsharedpartlistsum(Integer jqcode, Integer qccode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sum(s.predictionnumber) predictionnumber,sum(s.maxnumber) maxnumber,sum(s.plannumber) plannumber from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where d.jqcode=? and d.qccode=?";
		paramlist.add(jqcode);
		paramlist.add(qccode);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartlistsum(Integer qccode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		//String sql = "select sum(s.predictionnumber) predictionnumber,sum(s.maxnumber) maxnumber,sum(s.plannumber) plannumber from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where d.qccode=?";
		String sql = "select sum(d.predictionnumber) as predictionnumber,sum(d.plannumber) as plannumber,sum(d.realnumber) realnumber,sum(d.initinventory) initinventory,d.type from dt_sumplan d where d.qccode=?";
		paramlist.add(qccode);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindsharedpartlistsum2(Integer bdcode, String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sum(d.predictionnumber) as predictionnumber,sum(d.plannumber) as plannumber,sum(d.realnumber) realnumber,sum(d.initinventory) initinventory,sum(d.currentinventory) currentinventory,d.type from dt_sumplan d where d.bdcode=? and d.qcname=?";
		paramlist.add(bdcode);
		paramlist.add(qcname);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	
	@Override
	public List adminfindsharedpartlistsum3(Integer jqcode,String bdcode,String qccode, String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sum(d.predictionnumber) as predictionnumber,sum(d.plannumber) as plannumber,sum(d.realnumber) as realnumber,sum(d.realsupply) as realsupply,d.initinventory as initinventory,d.currentinventory as currentinventory,d.type from dt_sumplan d where d.jqcode=? and d.qccode=? and d.qcname=?";
		paramlist.add(jqcode);
		//paramlist.add(bdcode);
		paramlist.add(qccode);
		paramlist.add(qcname);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	
	@Override
	public List adminfindsharedpartlistsum4(Integer jqcode,String bdcode,String qccode, String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sum(d.predictionnumber) as predictionnumber,sum(d.plannumber) as plannumber,d.realnumber realnumber,d.initinventory initinventory,d.currentinventory currentinventory,d.type from dt_sumplan d where d.jqcode=? and d.bdcode=? and d.qccode=? and d.qcname=?";
		paramlist.add(jqcode);
		paramlist.add(bdcode);
		paramlist.add(qccode);
		paramlist.add(qcname);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
	
	@Override
	public List adminfindsharedpartlistsum2(String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		//String sql = "select sum(s.predictionnumber) predictionnumber,sum(s.maxnumber) maxnumber,sum(s.plannumber) plannumber,sum(p.number) number,sum(s.realnumber) realnumber,d.type from dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id left join dt_plan p on d.dataprocess_id=p.dataprocess_id where d.qccode=?";
		String sql = "select sum(d.predictionnumber) as predictionnumber,sum(d.plannumber) as plannumber,sum(d.realnumber) realnumber,sum(d.initinventory) initinventory,sum(d.currentinventory) currentinventory,d.type from dt_sumplan d where d.qcname=?";
		paramlist.add(qcname);
		return dt_sharedpartdao.exesqlrelist(sql, paramlist);
	}
}
