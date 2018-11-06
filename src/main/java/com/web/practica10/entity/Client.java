package com.web.practica10.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @NotEmpty
    private String ced;

    private String phone;

    private String email;

    private String photo;

    private Boolean enabled;

    @OneToMany
    private Set<AppRole> rolSet;

    public Client() {
    }

    public Client(String name, String ced, String phone, String email, String photo, Boolean enabled, Set<AppRole> rolSet) {
        this.name = name;
        this.ced = ced;
        this.phone = phone;
        this.email = email;
        this.photo = photo;
        this.enabled = enabled;
        this.rolSet = rolSet;
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

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AppRole> getRolSet() {
        return rolSet;
    }

    public void setRolSet(Set<AppRole> rolSet) {
        this.rolSet = rolSet;
    }
}


