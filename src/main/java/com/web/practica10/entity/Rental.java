package com.web.practica10.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Rental implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String date;

    private String deliveryDate;

    @OneToOne
    private  Client client;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Equip> equipRental;

    private int equipStock;

    private int cost;

    private Boolean pending;


    public Rental() {
    }

    public Rental(String date, String deliveryDate, Client client, Set<Equip> equipRental, int equipStock, int cost, Boolean pending) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Equip> getEquipRental() {
        return equipRental;
    }

    public void setEquipRental(Set<Equip> equipRental) {
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
