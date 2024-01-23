package com.example.be1_practicecrud_managecustomer_usecrudrepository.service;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService extends IGenerateService<Customer> {
    Iterable<Customer> findAllByProvice(Provice provice);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFullName(Pageable pageable, String name);
    Page<Customer> findAllByAge(Pageable pageable, int age);
}
