package com.web.practica10.service;

import com.web.practica10.entity.Familia;
import com.web.practica10.repositories.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FamiliaService {

    @Autowired
    FamiliaRepository familiaRepository;

    @Transactional
    public void saveFam(Familia familia){
        familiaRepository.save(familia);
    }

    @Transactional
    public void deleteFam(Familia familia){
        familia.setEnabled(false);
        familiaRepository.save(familia);
    }

    @Transactional
    public void setPadre(Familia padre, Familia hijo){
        padre.setHijo(hijo);
        familiaRepository.save(padre);
    }

    public List<Familia> getFamilias(Boolean b){

        return familiaRepository.findAllByEnabled(b);
    }

    public Familia findFamilia(int id){
        return familiaRepository.findById(id);
    }
    public List<Familia> getFamiliasPadre(Boolean b){

        return familiaRepository.findAllByEnabledAndHijoNull(b);
    }
    public List<Familia> getSubFamilias(Boolean b){

        return familiaRepository.findByHijoNotNullAndEnabled(b);
    }

}
