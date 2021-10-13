package com.kapitalbank.task.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Timestamp time;
    @Column(columnDefinition = "NUMERIC(8,2)", nullable = false)
    private BigDecimal amount;

    public Payment(BigDecimal amount, Timestamp time) {
        this.amount = amount;
        this.time = time;
    }
}
