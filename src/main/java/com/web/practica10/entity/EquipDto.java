package com.web.practica10.entity;

import java.util.ArrayList;
import java.util.List;

public class EquipDto {


    private List<Equip> equipos;

    public EquipDto() {
        this.equipos = new ArrayList<>();
    }

    public EquipDto(List<Equip> equipos) {
        this.equipos = equipos;
    }

    public List<Equip> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equip> equipos) {
        this.equipos = equipos;
    }

    public void addEquipo(Equip book) {
        this.equipos.add(book);
    }
}
