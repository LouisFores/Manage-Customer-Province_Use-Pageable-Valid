package com.example.be1_practicecrud_managecustomer_usecrudrepository.service.impl;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.repository.ICustomerRepository;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }
    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void remote(Long id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvice(Provice provice) {
        return iCustomerRepository.findAllByProvice(provice);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFullName(Pageable pageable, String name) {
        return iCustomerRepository.findAllByFullNameContaining(pageable, name);
    }

    @Override
    public Page<Customer> findAllByAge(Pageable pageable, int age) {
        return iCustomerRepository.findAllByAge(pageable, age);
    }
}
