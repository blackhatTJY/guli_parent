package com.guli.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author tjy
 */

@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSujectName;

    @ExcelProperty(index = 1)
    private String twoSujectName;
}