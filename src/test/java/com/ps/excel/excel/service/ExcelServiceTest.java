package com.ps.excel.excel.service;


import com.ps.excel.excel.JobStatus;
import com.ps.excel.excel.dto.ExcelResponse;
import com.ps.excel.excel.entity.Excel;
import com.ps.excel.excel.repository.ExcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class ExcelServiceTest {

    @Autowired
    ExcelRepository excelRepository;

    @Autowired
    ExcelService excelService;


    @Test
    void 엑셀_생성_요청시_PENDING_상태로_저장된다(){

        // given

        // when
        ExcelResponse excelResponse = excelService.createExcel();

        // then
        assertThat(excelResponse.getStatus())
                .isEqualTo(JobStatus.PENDING);


    }

    @Test
    void 작업_조회시_ResponseDTO로_변환된다(){

        // given
        Excel excel1 = Excel.builder()
                .status(JobStatus.DONE)
                .requestedAt(LocalDateTime.now())
                .filepath("files/excel_1.xlsx")
                .build();

        Excel excel2 = Excel.builder()
                .status(JobStatus.PROCESSING)
                .requestedAt(LocalDateTime.now())
                .filepath("files/excel_2.xlsx")
                .build();

        excelRepository.save(excel1);
        excelRepository.save(excel2);

        // when
        List<ExcelResponse> list = excelService.getExcels();

        // then
        assertThat(list.get(1).getFilepath()).isEqualTo("files/excel_1.xlsx");
        assertThat(list.get(1).getStatus()).isEqualTo(JobStatus.DONE);
        assertThat(list.get(0).getFilepath()).isEqualTo("files/excel_2.xlsx");
        assertThat(list.get(0).getStatus()).isEqualTo(JobStatus.PROCESSING);
    }
}
