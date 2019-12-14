package com.autoecole.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoecole.entities.Condidat;
import com.autoecole.entities.Moniteur;
import com.autoecole.entities.Seance;


@Repository
public interface SeanceDAO extends JpaRepository<Seance, Long>{
	
	List<Seance> findByCondidat(Condidat condidat);
	List<Seance> findByMoniteur(Moniteur moniteur);

}
