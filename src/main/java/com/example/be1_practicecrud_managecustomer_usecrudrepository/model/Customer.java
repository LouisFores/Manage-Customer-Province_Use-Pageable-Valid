package com.example.be1_practicecrud_managecustomer_usecrudrepository.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Not null full name")
    private String fullName;

    @Min(value = 1, message = "Value > 0")
    private int age;

    @ManyToOne
    @JoinColumn(name = "provice_id")
    private Provice provice;

    public Customer() {}

    public Customer(Long id, String fullName, int age, Provice provice) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.provice = provice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Provice getProvice() {
        return provice;
    }

    public void setProvice(Provice provice) {
        this.provice = provice;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", provice=" + provice +
                '}';
    }
}
