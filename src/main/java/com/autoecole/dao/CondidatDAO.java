package com.autoecole.dao;

import com.autoecole.entities.Condidat;
import com.autoecole.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CondidatDAO extends JpaRepository<Condidat, Long>{
	
	Condidat findByUserid(User user);

}
