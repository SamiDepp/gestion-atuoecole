package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.CondidatDAO;
import com.autoecole.dao.UserDAO;
import com.autoecole.entities.Condidat;
import com.autoecole.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
