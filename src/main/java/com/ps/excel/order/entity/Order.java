package com.ps.excel.order.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user_name;

    private String product_name;

    private String category;

    private Integer amount;

    private String status;

    private LocalDateTime order_date;

}
