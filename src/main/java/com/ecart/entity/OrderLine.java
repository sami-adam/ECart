package com.ecart.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "order_lines")
@Data
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JoinColumn(name = "unit_price")
    private double unitPrice;
    private double quantity;
    @JoinColumn(name = "sub_total")
    private double discount;
    private double subTotal;
}
