package com.kapitalbank.task.controller;

import com.kapitalbank.task.dto.InvoiceIssuedBeforeOrderDTO;
import com.kapitalbank.task.dto.OverpaidInvoicesDTO;
import com.kapitalbank.task.entity.Invoice;
import com.kapitalbank.task.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> getAllInvoices()
    {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable int id)
    {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice)
    {
        return invoiceService.createInvoice(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable int id, @RequestBody Invoice invoice)
    {
        return invoiceService.updateInvoice(id, invoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable int id)
    {
        return invoiceService.deleteInvoice(id);
    }

    @GetMapping("/expired_invoices")
    public ResponseEntity<List<Invoice>> getExpiredInvoices()
    {
        return invoiceService.issuedAfterDue();
    }

    @GetMapping("/wrong_date_invoices")
    public ResponseEntity<List<InvoiceIssuedBeforeOrderDTO>> getWrongInvoices()
    {
        return invoiceService.getWrongDateInvoices();
    }

    @GetMapping("/overpaid_invoices")
    public ResponseEntity<List<OverpaidInvoicesDTO>> getOverpaidInvoices()
    {
        return invoiceService.getOverpaidInvoices();
    }


}
