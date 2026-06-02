package com.ps.excel.excel.entity;

import static org.assertj.core.api.Assertions.assertThat;
import com.ps.excel.excel.JobStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ExcelTest {

    @Test
    void 작업_시작시_PROCESSING_상태가_된다() {

        // given
        Excel excel = Excel.builder()
                .status(JobStatus.PENDING)
                .build();

        // when
        excel.start();

        // then
        assertThat(excel.getStatus()).isEqualTo(JobStatus.PROCESSING);
        assertThat(excel.getStartedAt()).isNotNull();

    }

    @Test
    void 작업_완료_후_DONE_상태가_된다() {

        // given
        Excel excel = Excel.builder()
                .status(JobStatus.PROCESSING)
                .build();

        // when
        excel.finish("files/excel_1.xlsx");

        // then
        assertThat(excel.getStatus()).isEqualTo(JobStatus.DONE);
        assertThat(excel.getFinishedAt()).isNotNull();
        assertThat(excel.getFilepath()).isEqualTo("files/excel_1.xlsx");

    }

    @Test
    void 작업_실패시_FAILED_상태가_된다() {
        // given
        Excel excel = Excel.builder()
                .status(JobStatus.PROCESSING)
                .build();

        // when
        excel.fail();

        // then
        assertThat(excel.getStatus()).isEqualTo(JobStatus.FAILED);

    }
}
