package com.web.practica10.repositories;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.EquipRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface EquipRentRepository extends JpaRepository<EquipRental, Integer> {

    EquipRental findById(int id);
    EquipRental findByEquip(Equip equip);
}
