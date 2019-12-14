package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.MoniteurDAO;
import com.autoecole.entities.Condidat;
import com.autoecole.entities.Moniteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoecole.dao.CondidatDAO;
import com.autoecole.dao.PaiementDAO;
import com.autoecole.entities.Payement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaiementServices {
	
	
	@Autowired
	private PaiementDAO repository ;
	
	@Autowired
	private MoniteurDAO monitrepository ;
	
	@Autowired
	private CondidatDAO condidrepository ;
	
	
	public List<Payement> listAll(){
		
		return repository.findAll() ;
	}
	
	public Payement create(Long condidatid, Long moniteurid, int total_conduit, double montant_total, int nbseance){
		
		Moniteur moniteur = monitrepository.getOne(moniteurid);
		Condidat condidat = condidrepository.getOne(condidatid);
		Payement pay = new Payement(condidat, moniteur, total_conduit, montant_total, nbseance);
		return repository.saveAndFlush(pay);
	}

}
