package com.hd.microblog.service;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_outtemp;

import java.util.List;


public interface fd_outtempService extends IBaseService<fd_outtemp, Integer> {

	List adminfinddataprocessslist();

	List adminfinddataprocesslist(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg, String sort,
			Integer start, int number);

	List adminfinddataprocesslistcount(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg);

	List adminfinddataprocessgroup(String fg);

	List adminfinddataprocesslistforqccode(String qccode);

	List getBySparepartnum(int sparepartnum);
}

