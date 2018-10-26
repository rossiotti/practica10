package com.web.practica10.repositories;

import com.web.practica10.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<AppRole,Integer> {
    AppRole findByRoleName(String rolName);
}
