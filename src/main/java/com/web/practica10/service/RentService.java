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

    @Transactional
    public void updateStatus(Rental rental,Boolean bo){

        rental.setPending(bo);
        rentRepositoy.save(rental);
    }
    @Transactional
    public void updateDate(Rental rental,String bo){

        rental.setDeliveryDate(bo);
        rentRepositoy.save(rental);
    }



    public List<Rental> rentalList(Boolean b){

        return rentRepositoy.findAllByPendingOrderByDeliveryDateDesc(b);
    }

    public List<Rental> rentalListAll(){

        return rentRepositoy.findAll();
    }


    @Transactional
    public void setDias(Rental rental, int dias){

        rental.setDiasRent(dias);
        rentRepositoy.save(rental);
    }

    @Transactional
    public void setCosto(Rental rental, float cost){

        rental.setCost(cost);
        rentRepositoy.save(rental);
    }

    public List<Rental> rentalsByClient(Client client){

        return rentRepositoy.findAllByClient(client);
    }

    public Rental findRental(int id){

        return rentRepositoy.findById(id);
    }
}
