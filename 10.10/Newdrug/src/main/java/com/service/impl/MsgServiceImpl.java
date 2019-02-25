package com.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DAO.BaseDAO;
import com.entity.Msg;
import com.service.MsgService;

@Service("msgService")
public class MsgServiceImpl implements MsgService {

	private static final Logger log = LoggerFactory.getLogger(MsgServiceImpl.class);

	@Resource
	private BaseDAO<Msg> msgDAO;
	@Override
	public String addMsg(Msg m) {
		Serializable s  = msgDAO.save(m);
		if(s==null){
			return "0";
		} 
		m.setId(Long.parseLong(s.toString()));
		return "1";
	}
	
	

}
