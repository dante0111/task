package com.kapitalbank.task.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name="detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "smallint", nullable = false)
    private short quantity;

    @ManyToOne
    @JoinColumn(name = "pr_id")
    private Product product;

    public Detail(short quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
}
