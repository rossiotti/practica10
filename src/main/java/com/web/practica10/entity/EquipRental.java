package com.web.practica10.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EquipRental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idequip_rent", unique = true, nullable = false)
    private int id;

    @OneToOne
    private Equip equip;

    public int getCantidadRentada() {
        return cantidadRentada;
    }

    public void setCantidadRentada(int cantidadRentada) {
        this.cantidadRentada = cantidadRentada;
    }

    private int cantidadRentada;

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    private int dias;

    public float getCostoRenta() {
        return costoRenta;
    }

    public void setCostoRenta(float costoRenta) {
        this.costoRenta = costoRenta;
    }

    private float costoRenta;

    private Boolean returned;

    public EquipRental() {

    }

    public EquipRental(Equip equip, int cantidadRentada, int dias, float costoRenta, Boolean returned) {
        this.equip = equip;
        this.cantidadRentada = cantidadRentada;
        this.dias = dias;
        this.costoRenta = costoRenta;
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
