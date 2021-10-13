package com.kapitalbank.task.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@ToString
@Table(name = "cust_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date date;

    @OneToMany
    @JoinColumn(name = "ord_id")
    private List<Detail> details;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public Order(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public Order(Date date, List<Detail> details, Customer customer) {
        this.date = date;
        this.details = details;
        this.customer = customer;
    }

    public void addDetail(Detail detail)
    {
        if(details == null)
            details = new ArrayList<>();

        details.add(detail);
    }
}
