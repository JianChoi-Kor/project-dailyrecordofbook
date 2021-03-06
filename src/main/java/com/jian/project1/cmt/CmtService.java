package com.jian.project1.cmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.project1.model.CmtDomain;
import com.jian.project1.model.CmtEntity;

@Service
public class CmtService {
	
	@Autowired
	private CmtMapper mapper;
	
	
	public int insCmt(CmtEntity p) {
		return mapper.insCmt(p);
	}
	
	public List<CmtDomain> selCmtList(CmtEntity p) {
		return mapper.selCmtList(p);
	}
}
