package com.example.be1_practicecrud_managecustomer_usecrudrepository.service.impl;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.repository.IProviceRepository;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.ICustomerService;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.IProviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviceService implements IProviceService {
    @Autowired
    private IProviceRepository iProviceRepository;


    @Override
    public Iterable<Provice> findAll() {
        return iProviceRepository.findAll();
    }

    @Override
    public Optional<Provice> findById(Long id) {
        return iProviceRepository.findById(id);
    }

    @Override
    public void save(Provice provice) {
        iProviceRepository.save(provice);
    }

    @Override
    public void remote(Long id) {
        iProviceRepository.deleteById(id);
    }
}
