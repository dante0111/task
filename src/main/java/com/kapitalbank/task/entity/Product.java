package com.kapitalbank.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(10)")
    private String name;
    @Column(columnDefinition = "varchar(20)")
    private String description;
    @Column(columnDefinition = "NUMERIC(6,2)", nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "varchar(1024)")
    private String photo;

    public Product(String description, String name, String photo, BigDecimal price) {
        this.description = description;
        this.photo = photo;
        this.name = name;
        this.price = price;
    }
}
