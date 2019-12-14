package com.autoecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoecole.entities.Roles;


@Repository
public interface RoleDAO extends JpaRepository<Roles, Long>{

}
