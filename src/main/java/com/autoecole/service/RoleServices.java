package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.RoleDAO;
import com.autoecole.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleServices {
	
	
	@Autowired
	private RoleDAO repository ;
	
	

	public Roles getbyid(Long id) {
	Roles role = repository.getOne(id);
		return role;
	}


	
	public List<Roles> list() {
		
		return repository.findAll();
	}

}
