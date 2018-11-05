package com.web.practica10.repositories;

import com.web.practica10.entity.Equip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface EquipRepository  extends JpaRepository<Equip, Integer> {

    Equip findById(int id);

    List<Equip> findAllByEnabledAndStockGreaterThan(Boolean aBoolean, int stock);
}
