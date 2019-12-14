package com.autoecole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoecole.myapp.dao.RoleDAO;
import com.autoecole.myapp.entities.Roles;
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
