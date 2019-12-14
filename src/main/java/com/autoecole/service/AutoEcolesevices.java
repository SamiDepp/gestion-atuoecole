package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.AutoEcoleDAO;
import com.autoecole.entities.AutoEcole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AutoEcolesevices {

	@Autowired
	private AutoEcoleDAO repository ;
	
	

	public AutoEcole getbyid(Long id) {
		AutoEcole auto = repository.getOne(id);
		return auto;
	}


	
	public List<AutoEcole> list() {
		
		return repository.findAll();
	}

}
