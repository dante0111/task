package com.kapitalbank.task.service;

import com.kapitalbank.task.entity.Order;
import com.kapitalbank.task.entity.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    ResponseEntity<List<Payment>> getAllPayments();

    ResponseEntity<Payment> getPaymentById(int id);

    ResponseEntity<Map<String, Object>> createPayment(int invoice_id);

    ResponseEntity<Payment> updatePayment(int id, Payment payment);

    ResponseEntity<HttpStatus> deletePayment(int id);

}
