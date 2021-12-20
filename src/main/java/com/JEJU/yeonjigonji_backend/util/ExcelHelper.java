package com.JEJU.yeonjigonji_backend.util;

import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private static final String FILE_DETAIL_NAME = "src/main/resources/file/prd_refined_detail_name.xlsx";
    private static final String FILE_NAME = "src/main/resources/file/prd_refined_data_list.xlsx";

//    public static void main(String[] args) {
//        readItemExcel();
//        readDetailExcel();
//    }

    public static List<PrdItem> readItemExcel() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_DETAIL_NAME));
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

    public static List<PrdDetailItem> readDetailExcel() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
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
                                data.setId(Long.parseLong(id)); // id 동일하게 넣어넣고 db에 넣을 때 reset하게 (prdItem에서 찾기 위해)
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
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case 4:
                            String images = fmt.formatCellValue(currentCell);
                            if (!images.trim().isEmpty()) {
                                data.setImgTags(images);
                            }

                    }
                    cellIdx++;
                    if (cellIdx == 5) break;
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


