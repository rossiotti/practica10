package com.web.practica10.service;

import com.web.practica10.entity.Equip;
import com.web.practica10.repositories.EquipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class EquipService {

    @Autowired
    EquipRepository equipRepository;

    @Transactional
    public void createEquip(Equip equip){

        equipRepository.save(equip);

    }

    @Transactional
    public void deleteEquip(Equip equip){

        equip.setEnabled(false);
        equipRepository.save(equip);
    }

    public Equip findEquip(int id){

        return equipRepository.findById(id);

    }

    @Transactional
    public void editarStock(Equip equip, int stock, int stockRent){
        equip.setStockRent(stockRent);
        equip.setStock(stock);
        equipRepository.save(equip);
    }

    public List<Equip> listEquip(Boolean aBoolean, int stock){

        return equipRepository.findAllByEnabledAndStockGreaterThan(aBoolean,stock);
    }

}
