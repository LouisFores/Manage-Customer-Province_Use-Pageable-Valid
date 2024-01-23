package com.example.be1_practicecrud_managecustomer_usecrudrepository.repository;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvice(Provice provice);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFullNameContaining(Pageable pageable, String name);
    Page<Customer> findAllByAge(Pageable pageable,  int age);
}
