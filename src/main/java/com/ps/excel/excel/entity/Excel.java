package com.ps.excel.excel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "excel")
@Builder
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime requested_at;

    private LocalDateTime started_at;

    private LocalDateTime finished_at;

    private String status;

    private String filepath;

}
