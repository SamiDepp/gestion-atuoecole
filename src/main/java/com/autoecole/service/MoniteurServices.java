package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.MoniteurDAO;
import com.autoecole.dao.UserDAO;
import com.autoecole.entities.Moniteur;
import com.autoecole.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
