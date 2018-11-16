package com.web.practica10.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Equip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idequipo", unique = true, nullable = false)
    private int id;

    private String name;

    private float tariff;

    @OneToOne
    private Familia family;

    @OneToOne
    private Familia subFamily;

    private Boolean enabled;

    private int diasRentados;

    private float costoRenta;

    public int getDiasRentados() {
        return diasRentados;
    }

    public void setDiasRentados(int diasRentados) {
        this.diasRentados = diasRentados;
    }

    public float getCostoRenta() {
        return costoRenta;
    }

    public void setCostoRenta(float costoRenta) {
        this.costoRenta = costoRenta;
    }

    private String photo;

    private int stock;

    public int getStockRent() {
        return stockRent;
    }

    public void setStockRent(int stockRent) {
        this.stockRent = stockRent;
    }

    private int stockRent;

    public String getElegido() {
        return elegido;
    }

    public void setElegido(String elegido) {
        this.elegido = elegido;
    }

    private String elegido;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    public Equip() {
    }

    public Equip(String name, int tariff, Familia family, Familia subFamily, Boolean enabled, int diasRentados, float costoRenta, String photo, int stock, int stockRent, String elegido) {
        this.name = name;
        this.tariff = tariff;
        this.family = family;
        this.subFamily = subFamily;
        this.enabled = enabled;
        this.diasRentados = diasRentados;
        this.costoRenta = costoRenta;
        this.photo = photo;
        this.stock = stock;
        this.stockRent = stockRent;
        this.elegido = elegido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTariff() {
        return tariff;
    }

    public void setTariff(float tariff) {
        this.tariff = tariff;
    }

    public Familia getFamily() {
        return family;
    }

    public void setFamily(Familia family) {
        this.family = family;
    }

    public Familia getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(Familia subFamily) {
        this.subFamily = subFamily;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
