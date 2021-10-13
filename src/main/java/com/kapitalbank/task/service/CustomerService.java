package com.kapitalbank.task.service;

import com.kapitalbank.task.dto.CutomersLastOrdersDTO;
import com.kapitalbank.task.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<List<Customer>> getAllCustomers();

    ResponseEntity<Customer> getCustomerById(int id);

    ResponseEntity<Customer> createCustomer(Customer customer);

    ResponseEntity<Customer> updateCustomer(int id, Customer customer);

    ResponseEntity<HttpStatus> deleteCustomer(int id);

    List<Customer> findAll();

    ResponseEntity<List<Customer>> getCustomersWithoutOrders();

    ResponseEntity<List<CutomersLastOrdersDTO>> getCustomersLastOrders();
}
