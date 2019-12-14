package com.autoecole.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.autoecole.dao.CondidatDAO;
import com.autoecole.dao.ExamDAO;
import com.autoecole.dao.MoniteurDAO;
import com.autoecole.entities.Condidat;
import com.autoecole.entities.Examen;
import com.autoecole.entities.Moniteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamenServices {
	
	private static final Logger LoggerFactory = null;
	private static final Logger logger = Logger.getAnonymousLogger();
	
	@Autowired
	private ExamDAO repository ;
	
	@Autowired
	private CondidatDAO candidatrepository ;
	
	
	@Autowired
	private MoniteurDAO moniteurrepository ;
	
	
	public Examen getbyid(Long id){
		
		return repository.getOne(id);
		
		
	}
	
	
	
	public Examen create(Date datedeb ,Date datefin ,String type ,String resultat ,String candidat ,String moniteur ){
		
		Condidat candid = candidatrepository.getOne(Long.parseLong(candidat));
		Moniteur monit = moniteurrepository.getOne(Long.parseLong(moniteur));
		logger.info("moniteur to add"+monit.getId());
		Boolean result = true ;
		if(Long.parseLong(resultat) == 0 ){
			
			result = false ;	
			
		}
		Examen exam = new Examen(datedeb, datefin, type, result, candid, monit);
		return repository.saveAndFlush(exam);
		
		
	}
	public List<Examen> listAll(){
		
		return repository.findAll() ;
	}

}
