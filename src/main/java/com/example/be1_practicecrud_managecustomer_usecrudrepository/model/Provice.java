package com.example.be1_practicecrud_managecustomer_usecrudrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "provice")
public class Provice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Provice(){}
    public Provice(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
