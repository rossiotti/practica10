package com.web.practica10.entity;

import javax.persistence.*;

@Entity
public class Familia {

    @Id
    @GeneratedValue
    @Column(name = "idfamilia", unique = true, nullable = false)
    private int id;

    private String nombre;

    @OneToOne
    private Familia hijo;

    private String descripcion;

    public Familia(String nombre, Familia hijo, String descripcion, Boolean enabled) {
        this.nombre = nombre;
        this.hijo = hijo;
        this.descripcion = descripcion;
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    Boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Familia getHijo() {
        return hijo;
    }

    public void setHijo(Familia hijo) {
        this.hijo = hijo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Familia() {
    }

}
