package com.ps.excel.excel.controller;

import com.ps.excel.excel.JobStatus;
import com.ps.excel.excel.entity.Excel;
import com.ps.excel.excel.repository.ExcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ExcelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExcelRepository excelRepository;

    @Test
    void 엑셀생성_요청시_200을_반환하고_DB에저장된다() throws Exception {
        mockMvc.perform(post("/api/excel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PENDING"));

        List<Excel> excels = excelRepository.findAll();
        assertThat(excels.getLast().getStatus()).isIn(JobStatus.PENDING, JobStatus.PROCESSING);
    }

    @Test
    void 작업목록_조회시_200을_반환한다() throws Exception {
        Excel excel = excelRepository.saveAndFlush(
                Excel.builder()
                        .status(JobStatus.DONE)
                        .requestedAt(LocalDateTime.now())
                        .build()
        );

        mockMvc.perform(get("/api/excel"))
                .andExpect(status().isOk());
    }
}
