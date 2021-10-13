package com.kapitalbank.task.service;

import com.kapitalbank.task.dto.NumberOfProductsInYearDTO;
import com.kapitalbank.task.dto.OrderDetailsAndProductName;
import com.kapitalbank.task.dto.OrdersWithoutInvoicesDTO;
import com.kapitalbank.task.entity.Invoice;
import com.kapitalbank.task.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {

    ResponseEntity<List<Order>> getAllOrders();

    ResponseEntity<Order> getOrderById(int id);

    ResponseEntity<Order> createOrder(Order order);

    ResponseEntity<Map<String, Object>> createOrderByParams(int customer_id, int product_id, short quantity);

    ResponseEntity<Order> updateOrder(int id, Order order);

    ResponseEntity<HttpStatus> deleteOrder(int id);

    ResponseEntity<List<Order>> getOrdersWithoutDetails();

    ResponseEntity<List<NumberOfProductsInYearDTO>> getNumberOfOrdersInOneYear();

    ResponseEntity<List<OrdersWithoutInvoicesDTO>> getOrdersWithoutInvoices();

    ResponseEntity<List<OrderDetailsAndProductName>> getOrderDetailsAndProductName(int id);
}
