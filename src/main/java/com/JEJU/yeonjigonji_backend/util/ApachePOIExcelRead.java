package com.JEJU.yeonjigonji_backend.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ApachePOIExcelRead {
    private static final String FILE_NAME = "src/main/resources/file/prd_refined_detail_name.xlsx";

    public static void main(String[] args) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            DataFormatter fmt = new DataFormatter();

            while (iterator.hasNext()) { // 행 하나씩
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator(); // 열 하나씩

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    switch (currentCell.getCellTypeEnum()) {
                        case _NONE:
                            break;
                        case NUMERIC:
                            String value = fmt.formatCellValue(currentCell);
                            if (!value.trim().isEmpty()) {
                                System.out.print(value + "\t");
                            }
                            break;
                        case STRING:
                            System.out.print(currentCell.getStringCellValue() + "\t");
                            break;
                        case BLANK:
                            break;
                    }
                }
                System.out.print("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


