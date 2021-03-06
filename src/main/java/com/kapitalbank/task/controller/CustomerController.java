package com.kapitalbank.task.controller;

import com.kapitalbank.task.dto.CutomersLastOrdersDTO;
import com.kapitalbank.task.entity.Customer;
import com.kapitalbank.task.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/list")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id)
    {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
    {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id)
    {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/customers_without_orders")
    public ResponseEntity<List<Customer>> getCustomersWithoutOrders()
    {
        return customerService.getCustomersWithoutOrders();
    }

    @GetMapping("/customers_last_orders")
    public ResponseEntity<List<CutomersLastOrdersDTO>> getCustomersLastOrders() {
        return customerService.getCustomersLastOrders();
    }
}
