package com.autoecole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoecole.myapp.dao.CondidatDAO;
import com.autoecole.myapp.dao.UserDAO;
import com.autoecole.myapp.entities.Condidat;
import com.autoecole.myapp.entities.User;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CondidatServices {
	
	
	@Autowired
	private CondidatDAO repository ;
	
	@Autowired
	private UserDAO userrespository ;
	
	
	
	public List<Condidat> listAll(){
		
		return repository.findAll();
		
		
	}
	
	
	public Condidat getbyuser(Long id){
		User us = userrespository.getOne(id);
		
		return repository.findByUserid(us);
		
	}

}
