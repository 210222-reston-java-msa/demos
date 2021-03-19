package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.DAO.RTypeDao;
import com.revature.models.RType;

public class RTypeService {
	
	static RTypeDao typeDao = new RTypeDao();

	public List<RType> getAll(){
		List<RType> types = new ArrayList<RType>();
		types = typeDao.findALL();		
		return types;
	}
	
	public String getRTypeById(int id) {
		String type = null;
		type = typeDao.findTypeById(id);
		return type;
	}
}
