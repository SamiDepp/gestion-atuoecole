package com.autoecole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoecole.myapp.dao.MoniteurDAO;
import com.autoecole.myapp.dao.UserDAO;
import com.autoecole.myapp.entities.Moniteur;
import com.autoecole.myapp.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoniteurServices {
	
	
	@Autowired
	private MoniteurDAO respository ;
	
	@Autowired
	private UserDAO userrespository ;
	
	
	public List<Moniteur> list(){
		
		
		return respository.findAll();
	}
	public void delete(Long id){
		
		Moniteur monit = respository.getOne(id);
		User us = userrespository.getOne(monit.getUserid().getId());
		respository.delete(monit);
		userrespository.delete(us);
		
		
	}
	
	public Moniteur getbyid(Long id){
		return respository.getOne(id);
		
	}
	
	public Moniteur getbyuser(Long id){
		User us = userrespository.getOne(id);
		
		return respository.findByUserid(us);
		
	}
	
	

}
