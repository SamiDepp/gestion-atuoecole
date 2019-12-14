package com.autoecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoecole.entities.Vehicule;



@Repository
public interface VehiculeDAO extends JpaRepository<Vehicule, Long> {

}
