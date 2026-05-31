package com.ps.excel.excel.service;

import com.ps.excel.excel.JobStatus;
import com.ps.excel.excel.dto.CreateExcelResponse;
import com.ps.excel.excel.dto.ExcelResponse;
import com.ps.excel.excel.entity.Excel;
import com.ps.excel.excel.repository.ExcelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExcelService {
    private final ExcelRepository excelRepository;

    public CreateExcelResponse createExcel(){
        Excel excel = Excel.builder()
                .status(JobStatus.PENDING)
                .requested_at(LocalDateTime.now())
                .build();

        Excel savedExcel = excelRepository.save(excel);

        return new CreateExcelResponse(
                savedExcel.getId(),
                savedExcel.getStatus()
        );
    }

    public List<ExcelResponse> getExcels(){
        return excelRepository.findAll().stream().map(excel -> new ExcelResponse(
                excel.getId(),
                excel.getStatus(),
                excel.getRequested_at(),
                excel.getStarted_at(),
                excel.getFinished_at(),
                excel.getFilepath()
        )).toList();
    }

}
