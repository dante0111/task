package com.kapitalbank.task.repository;

import com.kapitalbank.task.dto.InvoiceIssuedBeforeOrderDTO;
import com.kapitalbank.task.dto.OverpaidInvoicesDTO;
import com.kapitalbank.task.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(
            value = "SELECT * " +
                    "FROM invoice u " +
                    "WHERE u.issued > u.due",
            nativeQuery = true)
    List<Invoice> issuedAfterDue();

    @Query(name = "InvoiceIssuedBeforeOrder",
            nativeQuery = true)
    List<InvoiceIssuedBeforeOrderDTO> getWrongDateInvoices();

    @Query(name = "OverpaidInvoicesDTO",
    nativeQuery = true)
    List<OverpaidInvoicesDTO> getOverpaidInvoices();
}
