package com.web.practica10.repositories;

import com.web.practica10.entity.Familia;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia,Integer> {

    Familia findById(int id);
    List<Familia> findByHijoNotNullAndEnabled(Boolean boo);
    List<Familia> findAllByEnabled(Boolean boo);
    List<Familia> findAllByEnabledAndHijoNull(Boolean boo);
}
