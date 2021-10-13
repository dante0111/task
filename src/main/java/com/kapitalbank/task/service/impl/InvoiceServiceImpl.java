package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.dto.InvoiceIssuedBeforeOrderDTO;
import com.kapitalbank.task.dto.OverpaidInvoicesDTO;
import com.kapitalbank.task.entity.Invoice;
import com.kapitalbank.task.entity.Order;
import com.kapitalbank.task.repository.InvoiceRepository;
import com.kapitalbank.task.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        try {
            List<Invoice> invoices = invoiceRepository.findAll();

            return new ResponseEntity<>(invoices, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Invoice> getInvoiceById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent())
        {
            return new ResponseEntity<>(invoice.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Invoice> createInvoice(Invoice invoice) {
        try {
            Invoice invoice1 = invoiceRepository
                    .save(new Invoice(invoice.getAmount(), invoice.getDue(), invoice.getIssued(), invoice.getPayments()));
            return new ResponseEntity<>(invoice1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(int id, Invoice invoice) {
        Optional<Invoice> invoice1 = invoiceRepository.findById(id);

        if (invoice1.isPresent()) {
            Invoice invoice2 = invoice1.get();
            invoice2.setAmount(invoice.getAmount());
            invoice2.setDue(invoice.getDue());
            invoice2.setIssued(invoice.getIssued());
            invoice2.setPayments(invoice.getPayments());
            return new ResponseEntity<>(invoiceRepository.save(invoice2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteInvoice(int id) {
        try {
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Invoice>> issuedAfterDue() {
        try {
            List<Invoice> invoices = invoiceRepository.issuedAfterDue();
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public ResponseEntity<List<OverpaidInvoicesDTO>> getOverpaidInvoices() {
        try {
            List<OverpaidInvoicesDTO> overpaidInvoices = invoiceRepository.getOverpaidInvoices();

            return new ResponseEntity<>(overpaidInvoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<InvoiceIssuedBeforeOrderDTO>> getWrongDateInvoices() {

        try {
            List<InvoiceIssuedBeforeOrderDTO> invoiceIssuedBeforeOrderDTOS = invoiceRepository.getWrongDateInvoices();

            return new ResponseEntity<>(invoiceIssuedBeforeOrderDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
