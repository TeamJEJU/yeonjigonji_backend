package com.JEJU.yeonjigonji_backend.util;

import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.service.PrdItemService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApachePOIExcelRead {
    private static final String FILE_NAME = "src/main/resources/file/prd_refined_detail_name.xlsx";
    private static final String FILE_DETAIL_NAME = "src/main/resources/file/prd_refined_data_list.xlsx";

    public static void main(String[] args) {
        readItemExcel(FILE_NAME);
        readDetailExcel(FILE_DETAIL_NAME);
    }

    static List<PrdItem> readItemExcel(String fileName) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            DataFormatter fmt = new DataFormatter();

            List<PrdItem> dataList = null;
            while (iterator.hasNext()) { // 행 하나씩
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator(); // 열 하나씩
                PrdItem data = new PrdItem();
                dataList = new ArrayList<>();

                int cellIdx = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    switch (cellIdx) {
                        case 0:
                            String id = fmt.formatCellValue(currentCell);
                            if (!id.trim().isEmpty()) {
                                data.setId(Long.parseLong(id));
                            }
                            break;
                        case 1:
                            String name = currentCell.getStringCellValue();
                            if (!name.trim().isEmpty()) {
                                data.setUntNm(name);
                            }
                            break;
                        case 2:
                            String color = currentCell.getStringCellValue();
                            if (!color.trim().isEmpty()) {
                                data.setColor(color);
                            }
                            break;
                    }
                    cellIdx++;
                }
                System.out.print(data + "\n");
                dataList.add(data);
            }

            workbook.close();
            return dataList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        return null;
    }

    static List<PrdDetailItem> readDetailExcel(String fileName) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            DataFormatter fmt = new DataFormatter();
            List<PrdDetailItem> dataList = new ArrayList<>();

            while (iterator.hasNext()) { // 행 하나씩
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator(); // 열 하나씩
                PrdDetailItem data = new PrdDetailItem();

                int cellIdx = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    switch (cellIdx) {
                        case 0:
                            String id = fmt.formatCellValue(currentCell);
                            if (!id.trim().isEmpty()) {
                                //data.setId(Long.parseLong(id));
                                //data.setPrdItem();
                            }
                            break;
                        case 1:
                            String brand = currentCell.getStringCellValue();
                            if (!brand.trim().isEmpty()) {
                                data.setBrandNm(brand);
                            }
                            break;
                        case 2:
                            String imgUri = currentCell.getStringCellValue();
                            if (!imgUri.trim().isEmpty()) {
                                data.setRepImg(imgUri);
                            }
                            break;
                        case 3:
                            String price = fmt.formatCellValue(currentCell);
                            if (!price.trim().isEmpty()) {
                                //System.out.print(price + "\n");
                                try {
                                    data.setPrice(Integer.parseUnsignedInt(price));
                                }catch (NumberFormatException e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                    }
                    cellIdx++;
                    if (cellIdx == 4) break;
                }
                System.out.print(data + "\n");
                dataList.add(data);
            }

            workbook.close();
            return dataList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        return null;
    }


}


