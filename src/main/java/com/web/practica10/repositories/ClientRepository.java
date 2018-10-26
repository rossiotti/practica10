package com.web.practica10.repositories;

import com.web.practica10.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByName(String name);
    List<Client>findAllByEnabled(Boolean enabled);
    Client findById(int id);

}
