package com.web.practica10.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idrental", unique = true, nullable = false)
    private int id;

    private String date;

    private String deliveryDate;

    @OneToOne
    private  Client client;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<EquipRental> equipRental;

    private float cost;

    public int getDiasRent() {
        return diasRent;
    }

    public void setDiasRent(int diasRent) {
        this.diasRent = diasRent;
    }

    private int diasRent;

    private Boolean pending;


    public Rental() {
    }

    public Rental(String date, String deliveryDate, Client client, Set<EquipRental> equipRental, int equipStock, int cost, int diasRent, Boolean pending, Boolean entrega) {
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.client = client;
        this.equipRental = equipRental;
        this.cost = cost;
        this.diasRent = diasRent;
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

    public Set<EquipRental> getEquipRental() {
        return equipRental;
    }

    public void setEquipRental(Set<EquipRental> equipRental) {
        this.equipRental = equipRental;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }
}
