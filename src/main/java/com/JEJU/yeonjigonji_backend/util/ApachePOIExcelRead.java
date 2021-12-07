package com.JEJU.yeonjigonji_backend.util;

import com.JEJU.yeonjigonji_backend.entity.PrdItem;
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
        readExcel(FILE_NAME);
    }

    static void readExcel(String fileName) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            DataFormatter fmt = new DataFormatter();

            while (iterator.hasNext()) { // 행 하나씩
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator(); // 열 하나씩
                PrdItem data = new PrdItem();

                for (int i = 0; i < 3; i++) {
                    Cell currentCell = cellIterator.next();
                    switch (i) {
                        case 0:
                            String id = fmt.formatCellValue(currentCell);
                            if (!id.trim().isEmpty()) {
                                data.setPrd_id(Long.parseLong(id));
                            }
                            break;
                        case 1:
                            String name = currentCell.getStringCellValue();
                            if (!name.trim().isEmpty()) {
                                data.setPrd_unt_nm(name);
                            }
                            break;
                        case 2:
                            String color = currentCell.getStringCellValue();
                            if (!color.trim().isEmpty()) {
                                data.setPrd_color(color);
                            }
                    }
                }
                System.out.print(data + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


