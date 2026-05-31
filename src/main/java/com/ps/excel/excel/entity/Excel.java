package com.ps.excel.excel.entity;

import com.ps.excel.excel.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "excel")
@Builder(toBuilder = true)
@Getter
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime requested_at;

    private LocalDateTime started_at;

    private LocalDateTime finished_at;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private String filepath;

}
