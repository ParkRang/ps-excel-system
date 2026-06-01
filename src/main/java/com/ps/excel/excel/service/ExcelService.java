package com.ps.excel.excel.service;

import com.ps.excel.excel.JobStatus;
import com.ps.excel.excel.dto.CreateExcelResponse;
import com.ps.excel.excel.dto.ExcelResponse;
import com.ps.excel.excel.entity.Excel;
import com.ps.excel.excel.repository.ExcelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
public class ExcelService {

    private final ExcelRepository excelRepository;
    private final ExcelGenerationService excelGenerationService;

    // createExcel + generateExcel 연결
    public ExcelResponse createExcel(){
        Excel excel = Excel.builder()
                .status(JobStatus.PENDING)
                .requestedAt(LocalDateTime.now())
                .build();

        Excel savedExcel = excelRepository.saveAndFlush(excel);
        excelGenerationService.generateExcel(
                savedExcel.getId()
        );

        return ExcelResponse.builder()
                .id(savedExcel.getId())
                .status(savedExcel.getStatus())
                .requestedAt(savedExcel.getRequestedAt())
                .startedAt(savedExcel.getStartedAt())
                .finishedAt(savedExcel.getFinishedAt())
                .build();
    }

    public List<ExcelResponse> getExcels(){
        return excelRepository.findAll().stream().map(excel -> new ExcelResponse(
                excel.getId(),
                excel.getStatus(),
                excel.getRequestedAt(),
                excel.getStartedAt(),
                excel.getFinishedAt(),
                excel.getFilepath()
        )).toList();
    }

}
