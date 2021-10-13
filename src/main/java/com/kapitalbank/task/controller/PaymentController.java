package com.kapitalbank.task.controller;

import com.kapitalbank.task.entity.Order;
import com.kapitalbank.task.entity.Payment;
import com.kapitalbank.task.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Payment>> getAllPayments()
    {
        return paymentService.getAllPayments();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id)
    {
        return paymentService.getPaymentById(id);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestParam("invoice_id") int invoice_id)
    {
        return paymentService.createPayment(invoice_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int id, @RequestBody Payment payment)
    {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable int id)
    {
        return paymentService.deletePayment(id);
    }
}
