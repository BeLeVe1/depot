package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_datarecord;


public interface dt_datarecordService  extends IBaseService<dt_datarecord, Integer> {

	List adminfinddatarecordforrecordtime(Long starttime, Long endtime,Integer dataprocess_id);

	List adminfinddatarecordlist(Integer start, int number, Integer dataprocess_id,Integer flag);

	List adminfinddatarecordlistcount(Integer dataprocess_id,Integer flag);

}

