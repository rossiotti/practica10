package com.web.practica10.repositories;

import com.web.practica10.entity.Client;
import com.web.practica10.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepositoy extends JpaRepository<Rental, Integer> {

    Rental findById(int id);


    List<Rental> findAllByClient(Client client);

    List<Rental> findAllByPendingOrderByDeliveryDateDesc(Boolean aBoolean);
}
