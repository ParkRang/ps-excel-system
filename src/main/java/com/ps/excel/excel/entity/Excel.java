package com.ps.excel.excel.entity;

import com.ps.excel.excel.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "excel")
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private String filepath;

    // status 전환

    public void start(){
        this.status = JobStatus.PROCESSING;
        this.startedAt = LocalDateTime.now();
    }

    public void finish(String filepath){
        this.status = JobStatus.DONE;
        this.finishedAt = LocalDateTime.now();
        this.filepath = filepath;
    }

    public void fail(){
        this.status = JobStatus.FAILED;
    }

}
