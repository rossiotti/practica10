package com.web.practica10.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Equip implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private float tariff;

    private String family;

    private String subFamily;

    private Boolean enabled;

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

    public Equip(String name, int tariff, String family, String subFamily, Boolean enabled, String photo, int stock, int stockRent, String elegido) {
        this.name = name;
        this.tariff = tariff;
        this.family = family;
        this.subFamily = subFamily;
        this.enabled = enabled;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(String subFamily) {
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
