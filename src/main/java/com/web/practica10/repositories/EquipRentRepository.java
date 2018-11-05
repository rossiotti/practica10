package com.web.practica10.repositories;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.EquipRental;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

@Entity
public interface EquipRentRepository extends JpaRepository<EquipRental, Integer> {

    EquipRental findById(int id);
    EquipRental findByEquip(Equip equip);
}
