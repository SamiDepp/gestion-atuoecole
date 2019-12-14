package com.autoecole.service;

import java.util.List;

import com.autoecole.dao.VehiculeDAO;
import com.autoecole.entities.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VehiculeServices {
	
	
	@Autowired
	private VehiculeDAO repository ;
	
	
	public List<Vehicule> listall(){
		
		return repository.findAll();
	}
	
	
	
	public Vehicule save(String matricule, String type,
			String marque, String couleur,String assurance){
		
		Vehicule vehicule = new Vehicule(matricule, type, marque, couleur,assurance);
		return repository.saveAndFlush(vehicule);
		
		
	}
	
	
	public Vehicule getbyid(Long id){
		
	
		return repository.getOne(id);
		
		
	}
	
	public void delete(Long id){
		
		Vehicule vehicule = getbyid(id);
		
		
		 repository.delete(vehicule);
		
		
	}
	
	public void update(Long id ,String matricule, String type,
			String marque, String couleur,String assurance){
		
		Vehicule vehicule = getbyid(id);
		
		vehicule.setAssurance(assurance);
		vehicule.setCouleur(couleur);
		vehicule.setMatricule(matricule);
		vehicule.setMarque(marque);
		vehicule.setModele(type);
		vehicule.setType(type);
		
		 repository.saveAndFlush(vehicule);
		
		
	}


}
