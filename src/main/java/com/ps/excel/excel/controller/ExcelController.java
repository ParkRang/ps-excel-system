package com.ps.excel.excel.controller;

import com.ps.excel.excel.dto.CreateExcelResponse;
import com.ps.excel.excel.dto.ExcelResponse;
import com.ps.excel.excel.service.ExcelGenerationService;
import com.ps.excel.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService excelService;
    private final ExcelGenerationService excelGenerationService;

    @PostMapping
    public ResponseEntity<ExcelResponse> createExcel(){
        return ResponseEntity.ok(excelService.createExcel());
    }

    @GetMapping
    public List<ExcelResponse> getExcels(){
        return excelService.getExcels();
    }

    // 테스트
    @PostMapping("/{id}/generate")
    public ResponseEntity<String> generateExcel(@PathVariable int id){
        excelGenerationService.generateExcel(id);

        return ResponseEntity.ok("생성 요청 완료");
    }

}
