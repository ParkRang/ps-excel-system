package com.ps.excel.excel.service;

import com.ps.excel.excel.entity.Excel;
import com.ps.excel.excel.repository.ExcelRepository;
import com.ps.excel.order.entity.Order;
import com.ps.excel.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExcelGenerationService {

    private final ExcelRepository excelRepository;
    private final OrderRepository orderRepository;

    // 엑셀 작업 관리
    public void generateExcel(int excelId){
        Excel excel = excelRepository.findById(excelId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 작업입니다."));



        try {

            // 작업 start
            excel.start();

            // 데이터 조회
            List<Order> orders = orderRepository.findAll();

            // 생성 완료
            String filePath = createExcelFile(excelId, orders);
            excel.finish(filePath);

        } catch (Exception e) {
            excel.fail();
            throw new RuntimeException(e);
        }
    }

    // 엑셀 생성
    public String createExcelFile(int excelId, List<Order> orders) throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook(100);

        try {


            Sheet sheet = workbook.createSheet("Orders");

            // 헤더 생성
            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("USER_NAME");
            headerRow.createCell(2).setCellValue("PRODUCT_NAME");
            headerRow.createCell(3).setCellValue("CATEGORY");
            headerRow.createCell(4).setCellValue("AMOUNT");
            headerRow.createCell(5).setCellValue("STATUS");
            headerRow.createCell(6).setCellValue("ORDER_DATE");

            // 데이터 생성
            int rowNum = 1;

            for (Order order : orders) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(order.getId());
                row.createCell(1).setCellValue(order.getUserName());
                row.createCell(2).setCellValue(order.getProductName());
                row.createCell(3).setCellValue(order.getCategory());
                row.createCell(4).setCellValue(order.getAmount());
                row.createCell(5).setCellValue(order.getStatus());
                row.createCell(6).setCellValue(order.getOrderDate().toString());
            }

            // 파일 경로 지정
            // TODO : C가 아닌 Docker 내부 경로로 저장
            Path dir = Paths.get("files");
            Files.createDirectories(dir);

//            String filepath = "excel_" + excelId + ".xlsx";

            Path filePath = dir.resolve(
                    "excel_" + excelId + ".xlsx"
            );

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                workbook.write(fos);
            }

            return filePath.toString();
        } finally {

            workbook.dispose();
            workbook.close();
        }
    }

}
