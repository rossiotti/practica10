package com.web.practica10.service;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.EquipRental;
import com.web.practica10.repositories.EquipRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EquipRentService {

    @Autowired
    EquipRentRepository equipRentRepository;

    @Transactional
    public void createEquipRent(EquipRental equipRental) {

        equipRentRepository.save(equipRental);
    }

    @Transactional
    public void updateEquipRent(EquipRental equipRental,float cost, int days) {

        equipRental.setCostoRenta(cost);
        equipRental.setDias(days);

        equipRentRepository.save(equipRental);
    }

    @Transactional
    public void updateStatus(EquipRental equipRental,Boolean status) {

        equipRental.setReturned(status);

        equipRentRepository.save(equipRental);
    }

    public EquipRental findEquipRental(int id){

        return equipRentRepository.findById(id);
    }

    public void returnEquip (int id){

        EquipRental equipRental = equipRentRepository.findById(id);
        equipRental.setReturned(!equipRental.getReturned());
        equipRentRepository.save(equipRental);
    }

    public List<Equip> getEquipRental(Set<EquipRental> equipRentalSet){

        List<Equip> equipList = new ArrayList<>();

        for (EquipRental equipRental : equipRentalSet){
            equipList.add(equipRental.getEquip());
        }
        return equipList;
    }
}
