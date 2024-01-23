package com.example.be1_practicecrud_managecustomer_usecrudrepository.controller;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.ICustomerService;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.IProviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provices")
public class ProviceController {
    @Autowired
    private IProviceService iProviceService;
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("")
    public ModelAndView listProvice() {
        ModelAndView modelAndView = new ModelAndView("/provice/list");
        Iterable<Provice> provices = iProviceService.findAll();
        modelAndView.addObject("provices", provices);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/provice/create");
        modelAndView.addObject("provice", new Provice());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("provice") Provice provice, RedirectAttributes redirectAttributes) {
        iProviceService.save(provice);
        redirectAttributes.addAttribute("message", "Create new privce successfully");
        return "redirect:/provices";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Provice> provice = iProviceService.findById(id);
        if (provice.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/provice/update");
            modelAndView.addObject("provice", provice.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("provice") Provice provice, RedirectAttributes attributes) {
        iProviceService.save(provice);
        attributes.addFlashAttribute("message", "Update provice successfully");
        return "redirect:/provices";
    }
    @GetMapping("/view-provice/{id}")
    public ModelAndView viewProvice(@PathVariable("id") Long id) {
        Optional<Provice> proviceOptional = iProviceService.findById(id);
        if (!proviceOptional.isPresent()) {
            return new ModelAndView("/error_404");
        }
        Iterable<Customer> customers = iCustomerService.findAllByProvice(proviceOptional.get());
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
