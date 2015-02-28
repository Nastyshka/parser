package com.gamedev.app.parser.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by MainUser on 28/02/2015.
 */
public class ExcelParser {
    public Map<String, String> parseExcel(String fileName) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                CellReference nameRef = new CellReference("B" + row.getRowNum());
                Cell nameCell = row.getCell(nameRef.getCol());
                CellReference engRef = new CellReference("C" + row.getRowNum());
                Cell engCell = row.getCell(engRef.getCol());
                if (nameCell != null && engCell != null) {
                    resultMap.put(nameCell.getStringCellValue(), engCell.getStringCellValue());
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
