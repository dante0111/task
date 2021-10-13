package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.dto.CutomersLastOrdersDTO;
import com.kapitalbank.task.entity.Customer;
import com.kapitalbank.task.repository.CustomerRepository;
import com.kapitalbank.task.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();

            return new ResponseEntity<>(customers, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent())
        {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        try {
            Customer customer1 = customerRepository
                    .save(new Customer(customer.getAddress(), customer.getCountry(), customer.getName(), customer.getPhone()));
            return new ResponseEntity<>(customer1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(int id, Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);

        if (customer1.isPresent()) {
            Customer customer2 = customer1.get();
            customer2.setName(customer.getName());
            customer2.setCountry(customer.getCountry());
            customer2.setAddress(customer.getAddress());
            customer2.setPhone(customer.getPhone());
            return new ResponseEntity<>(customerRepository.save(customer2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCustomer(int id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<List<Customer>> getCustomersWithoutOrders() {
        try {
            List<Customer> customers = customerRepository.getCustomersWithoutOrders();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<CutomersLastOrdersDTO>> getCustomersLastOrders() {
        try {
            List<CutomersLastOrdersDTO> lastOrders = customerRepository.getCustomersLastOrders();
            return new ResponseEntity<>(lastOrders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
