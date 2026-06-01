package com.ps.excel.excel.dto;

import com.ps.excel.excel.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateExcelResponse {

    private int id;
    private JobStatus status;

}
