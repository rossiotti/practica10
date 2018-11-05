package com.web.practica10.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
public class Rental implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private Date deliveryDate;

    @OneToOne
    private  Client client;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<EquipRental> equipRental;

    private int equipStock;

    private int cost;

    private Boolean pending;


    public Rental() {
    }

    public Rental(Date date, Date deliveryDate, Client client, Set<EquipRental> equipRental, int equipStock, int cost, Boolean pending) {
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.client = client;
        this.equipRental = equipRental;
        this.equipStock = equipStock;
        this.cost = cost;
        this.pending = pending;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<EquipRental> getEquipRental() {
        return equipRental;
    }

    public void setEquipRental(Set<EquipRental> equipRental) {
        this.equipRental = equipRental;
    }

    public int getEquipStock() {
        return equipStock;
    }

    public void setEquipStock(int equipStock) {
        this.equipStock = equipStock;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }
}
