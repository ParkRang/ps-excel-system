package com.ps.excel.excel.repository;

import com.ps.excel.excel.entity.Excel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<Excel,Integer> {

    List<Excel> findAll();
    List<Excel> findAllById(Integer id);

    Excel save(List<Excel> excel);

}
