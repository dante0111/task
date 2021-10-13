package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.entity.Invoice;
import com.kapitalbank.task.entity.Payment;
import com.kapitalbank.task.repository.InvoiceRepository;
import com.kapitalbank.task.repository.PaymentRepository;
import com.kapitalbank.task.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private InvoiceRepository invoiceRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentRepository.findAll();

            return new ResponseEntity<>(payments, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Payment> getPaymentById(int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isPresent())
        {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPayment(int invoice_id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoice_id);

            if (!invoice.isPresent()){
                result.put("payment_status", "FAILED");
                return ResponseEntity.ok(result);
            }
            Payment payment = new Payment(invoice.get().getAmount(), Timestamp.valueOf(LocalDateTime.now()));
            Payment newPayment = paymentRepository.save(payment);
            invoice.get().getPayments().add(newPayment);
            invoiceRepository.save(invoice.get());

            result.put("payment_status", "SUCCESS");
            result.put("payment_details", newPayment);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("payment_status", "FAILED");
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public ResponseEntity<Payment> updatePayment(int id, Payment payment) {
        Optional<Payment> payment1 = paymentRepository.findById(id);

        if (payment1.isPresent()) {
            Payment payment2 = payment1.get();
            payment2.setAmount(payment.getAmount());
            payment2.setTime(payment.getTime());
            return new ResponseEntity<>(paymentRepository.save(payment2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deletePayment(int id) {
        try {
            paymentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
