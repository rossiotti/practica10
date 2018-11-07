package com.web.practica10.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class EquipRental implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Equip equip;

    private Boolean returned;

    public EquipRental() {

    }

    public EquipRental(Equip equip, Boolean returned) {
        this.equip = equip;
        this.returned = returned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

}
