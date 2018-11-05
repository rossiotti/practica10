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

    private int tariff;

    private String family;

    private String subFamily;

    private Boolean enabled;

    private String photo;

    private int stock;

    public Equip() {
    }

    public Equip(String name, int tariff, String family, String subFamily, Boolean enabled, String photo) {
        this.name = name;
        this.tariff = tariff;
        this.family = family;
        this.subFamily = subFamily;
        this.enabled = enabled;
        this.photo = photo;
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

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
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
