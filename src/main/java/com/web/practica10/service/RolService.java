package com.web.practica10.service;

import com.web.practica10.entity.AppRole;
import com.web.practica10.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {
    @Autowired
    RolRepository rolRepository;

    @Transactional
    public void createRol(AppRole role){
        rolRepository.save(role);
    }

    public List<AppRole> listRoles(){
        return rolRepository.findAll();
    }
}
