package com.kapitalbank.task.controller;

import com.kapitalbank.task.dto.NumberOfProductsInYearDTO;
import com.kapitalbank.task.dto.OrderDetailsAndProductName;
import com.kapitalbank.task.dto.OrdersWithoutInvoicesDTO;
import com.kapitalbank.task.entity.Order;
import com.kapitalbank.task.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/list")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id)
    {
        return orderService.getOrderById(id);
    }

    @PostMapping("/order/details/{id}")
    public ResponseEntity<List<OrderDetailsAndProductName>> getOrderDetailsAndProductName(@PathVariable int id)
    {
        return orderService.getOrderDetailsAndProductName(id);
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> createOrderByParams(@RequestParam("customer_id") int customer_id,
                                                   @RequestParam("product_id") int product_id,
                                                   @RequestParam("quantity") short quantity)
    {
        return orderService.createOrderByParams(customer_id, product_id, quantity);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order)
    {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable int id)
    {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/orders_without_details")
    public ResponseEntity<List<Order>> getOrdersWithoutDetails()
    {
        return orderService.getOrdersWithoutDetails();
    }

    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<List<NumberOfProductsInYearDTO>> getNumberOfOrdersInOneYear()
    {
        return orderService.getNumberOfOrdersInOneYear();
    }

    @GetMapping("/orders_without_invoices")
    public ResponseEntity<List<OrdersWithoutInvoicesDTO>> getOrdersWithoutInvoices()
    {
        return orderService.getOrdersWithoutInvoices();
    }
}
