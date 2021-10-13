package com.kapitalbank.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(14)", nullable = false)
    private String name;
    @Column(columnDefinition = "char(3)", nullable = false)
    private String country;

    @Column(columnDefinition = "text")
    private String address;
    @Column(columnDefinition = "varchar(50)")
    private String phone;

    public Customer(String address, String country, String name, String phone) {
        this.address = address;
        this.country = country;
        this.name = name;
        this.phone = phone;
    }
}
