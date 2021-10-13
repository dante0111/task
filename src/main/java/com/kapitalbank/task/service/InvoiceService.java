package com.kapitalbank.task.service;

import com.kapitalbank.task.dto.InvoiceIssuedBeforeOrderDTO;
import com.kapitalbank.task.dto.OverpaidInvoicesDTO;
import com.kapitalbank.task.entity.Invoice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {

    ResponseEntity<List<Invoice>> getAllInvoices();

    ResponseEntity<Invoice> getInvoiceById(int id);

    ResponseEntity<Invoice> createInvoice(Invoice invoice);

    ResponseEntity<Invoice> updateInvoice(int id, Invoice invoice);

    ResponseEntity<HttpStatus> deleteInvoice(int id);

    ResponseEntity<List<Invoice>> issuedAfterDue();

    List<Invoice> findAll();

    ResponseEntity<List<InvoiceIssuedBeforeOrderDTO>> getWrongDateInvoices();

    ResponseEntity<List<OverpaidInvoicesDTO>> getOverpaidInvoices();
}
