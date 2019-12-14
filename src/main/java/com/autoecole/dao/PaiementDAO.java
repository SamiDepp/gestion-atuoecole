package com.autoecole.dao;

import com.autoecole.entities.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaiementDAO extends JpaRepository<Payement, Long>{

}
