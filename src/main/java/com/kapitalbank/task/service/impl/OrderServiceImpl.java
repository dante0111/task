package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.dto.NumberOfProductsInYearDTO;
import com.kapitalbank.task.dto.OrderDetailsAndProductName;
import com.kapitalbank.task.dto.OrdersWithoutInvoicesDTO;
import com.kapitalbank.task.entity.*;
import com.kapitalbank.task.repository.*;
import com.kapitalbank.task.service.OrderService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private DetailRepository detailRepository;
    private InvoiceRepository invoiceRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, DetailRepository detailRepository, InvoiceRepository invoiceRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.detailRepository = detailRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();

            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Order> getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent())
        {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetailsAndProductName>> getOrderDetailsAndProductName(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent())
        {
            List<Detail> details = order.get().getDetails();
            List<OrderDetailsAndProductName> orderDetailsAndProductNames = new ArrayList<>();
            for (Detail d:
                 details) {
                OrderDetailsAndProductName orderDetailsAndProductName = new OrderDetailsAndProductName(d, d.getProduct().getName());
                orderDetailsAndProductNames.add(orderDetailsAndProductName);
            }

            return new ResponseEntity<>(orderDetailsAndProductNames, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        try {
            Order order1 = orderRepository
                    .save(new Order(order.getDate(), order.getDetails(), order.getCustomer()));
            return new ResponseEntity<>(order1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Order> updateOrder(int id, Order order) {
        Optional<Order> order1 = orderRepository.findById(id);

        if (order1.isPresent()) {
            Order order2 = order1.get();
            order2.setDate(order.getDate());
            order2.setDetails(order.getDetails());
            order2.setCustomer(order.getCustomer());
            return new ResponseEntity<>(orderRepository.save(order2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteOrder(int id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Order>> getOrdersWithoutDetails() {
        try {
            List<Order> orders = orderRepository.ordersWithoutDetails(Date.valueOf("2016-09-06"));
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<NumberOfProductsInYearDTO>> getNumberOfOrdersInOneYear() {
        try {
            List<NumberOfProductsInYearDTO> numberOfProductsInYearDTOS = orderRepository.getNumberOfOrdersInOneYear();
            return new ResponseEntity<>(numberOfProductsInYearDTOS, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrdersWithoutInvoicesDTO>> getOrdersWithoutInvoices() {
        try {
            List<OrdersWithoutInvoicesDTO> ordersWithoutInvoicesDTOS = orderRepository.getOrdersWithoutInvoices();
            return new ResponseEntity<>(ordersWithoutInvoicesDTOS, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> createOrderByParams(int customer_id, int product_id, short quantity) {

        Map<String, Object> result = new HashMap<>();
        try {
                Optional<Product> product = productRepository.findById(product_id);
                Optional<Customer> customer = customerRepository.findById(customer_id);

                if(!(customer.isPresent() && product.isPresent()))
                {
                    result.put("status", "FAILED");
                    return ResponseEntity.ok(result);
                }

                Detail detail = new Detail(quantity, product.get());
                Detail detail1 = detailRepository.save(detail);

                Order order = new Order(Date.valueOf(LocalDate.now()), customer.get());
                order.addDetail(detail1);
                Order madeOrder = orderRepository.save(order);

                Invoice invoice = new Invoice(product.get().getPrice().multiply(BigDecimal.valueOf(quantity)),
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())),
                        madeOrder);

                Invoice madeInvoice = invoiceRepository.save(invoice);

            result.put("status", "SUCCESS");
            result.put("invoice_number", madeInvoice.getId());

            return ResponseEntity.ok(result);
        }catch (Exception e)
        {
            result.put("status", "FAILED");
            return ResponseEntity.ok(result);
        }
    }
}
