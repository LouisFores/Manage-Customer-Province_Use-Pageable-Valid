package com.example.be1_practicecrud_managecustomer_usecrudrepository.controller;

import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Customer;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.model.Provice;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.repository.IProviceRepository;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.ICustomerService;
import com.example.be1_practicecrud_managecustomer_usecrudrepository.service.IProviceService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IProviceService iProviceService;
    @ModelAttribute("provices")
    public Iterable<Provice> listProvice() {
        return iProviceService.findAll();
    }

    public ModelAndView showList(Pageable pageable) {
        Page<Customer> customers = iCustomerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("")
    public ModelAndView listCustomer(@PageableDefault(value = 3)Pageable pageable) {
        return showList(pageable);
    }
    @GetMapping("/desc")
    public ModelAndView listCustomerDesc(@PageableDefault(value = 3)Pageable pageable) {
        pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "age"));
        return showList(pageable);
    }
    @GetMapping("/asc")
    public ModelAndView listCustomerAsc(Pageable pageable) {
        pageable = PageRequest.of(0, 4, Sort.by(Sort.Direction.ASC, "age"));
        return showList(pageable);
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult result,
                               Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        if (result.hasErrors()) {
            return new ModelAndView("/customer/create");
        }
        iCustomerService.save(customer);
        pageable = PageRequest.of(0, 3);
        return listCustomer(pageable);
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Customer> customer = iCustomerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        iCustomerService.save(customer);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Optional<Customer> customer = iCustomerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        iCustomerService.remote(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/customers";
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam("search") Optional<String> search, Pageable pageable) {
        Page<Customer> customers;
        if (search.isPresent()) {
            customers = iCustomerService.findAllByFullName(pageable, search.get());
            //customers.forEach(customer -> System.out.println(customer));
        } else {
            customers = iCustomerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @PostMapping("/searchAge ")
    public ModelAndView searchByAge(@RequestParam("searchAge") int searchAge, Pageable pageable) {
        Page<Customer> customers;
        if (searchAge <= 0) {
            customers = iCustomerService.findAll(pageable);
        } else {
            customers = iCustomerService.findAllByAge(pageable, searchAge);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;

    }
}
