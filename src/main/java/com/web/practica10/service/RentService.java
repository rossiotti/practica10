package com.web.practica10.service;

import com.web.practica10.entity.Client;
import com.web.practica10.entity.Rental;
import com.web.practica10.repositories.RentRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentService {

    @Autowired
    RentRepositoy rentRepositoy;

    @Transactional
    public void createRent(Rental rental){

        rentRepositoy.save(rental);
    }

    public List<Rental> rentalList(){

        return rentRepositoy.findAllByPendingOrderByDeliveryDateDesc(true);
    }

    public List<Rental> clientList(Client client){

        return rentRepositoy.findAllByClient(client);
    }

    public Rental findRental(int id){

        return rentRepositoy.findById(id);
    }
}