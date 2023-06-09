package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_datarecordDao;
import com.hd.microblog.model.dt_datarecord;
import com.hd.microblog.service.dt_datarecordService;
@Service("dt_datarecordService")
public class dt_datarecordServiceImpl extends BaseService<dt_datarecord, Integer> implements dt_datarecordService{
	private dt_datarecordDao dt_datarecorddao;
	@Autowired
	@Qualifier("dt_datarecordDao")
	@Override
	public void setBaseDao(IBaseDao<dt_datarecord, Integer> dt_datarecorddao) {
		this.baseDao = dt_datarecorddao;
		this.dt_datarecorddao = (dt_datarecordDao) dt_datarecorddao;
	}
	@Override
	public List adminfinddatarecordforrecordtime(Long starttime, Long endtime,Integer dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select COALESCE(sum(number),0) number from dt_datarecord where recordtime>=? and recordtime<? and dataprocess_id=? and flag=0 ";
		paramlist.add(starttime);
		paramlist.add(endtime);
		paramlist.add(dataprocess_id);
		return dt_datarecorddao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddatarecordlist(Integer start, int number, Integer dataprocess_id,Integer flag) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select d.*,dp.qcname,dp.qccode from dt_datarecord d left join dt_dataprocess dp on dp.dataprocess_id=d.dataprocess_id where d.dataprocess_id=? and d.flag=? order by d.createtime desc ";
		paramlist.add(dataprocess_id);
		paramlist.add(flag);
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return dt_datarecorddao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddatarecordlistcount(Integer dataprocess_id,Integer flag) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_datarecord where dataprocess_id=? and flag=? ";
		paramlist.add(dataprocess_id);
		paramlist.add(flag);
		return dt_datarecorddao.exesqlrelist(sql, paramlist);
	}
}
