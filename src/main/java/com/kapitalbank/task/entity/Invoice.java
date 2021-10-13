package com.kapitalbank.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Table(name="Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "NUMERIC(8,2)", nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date due;

    @OneToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @OneToMany
    @JoinColumn(name = "inv_id")
    private List<Payment> payments;

    public Invoice(BigDecimal amount, Date issued, Date due, Order order) {
        this.amount = amount;
        this.issued = issued;
        this.due = due;
        this.order = order;
    }

    public Invoice(BigDecimal amount, Date issued, Date due, List<Payment> payments) {
        this.payments = payments;
        this.amount = amount;
        this.due = due;
        this.issued = issued;
    }
}
