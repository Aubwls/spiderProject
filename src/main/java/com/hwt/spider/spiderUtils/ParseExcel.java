package com.hwt.spider.spiderUtils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hwt
 * @date: 2020/11/4
 * @description:
 */
public class ParseExcel {

    private static final String xlsxSuffix = "xlsx";

    private static final String xlsSuffix = "xls";

    private static Map parseXSSF(InputStream is){
        XSSFWorkbook sheets = null;
        FormulaEvaluator formulaEvaluator = null;
        Map<String, Map> sheetMap = null;
        try {
            sheets = new XSSFWorkbook(is);
            formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) sheets);
            sheetMap = new HashMap();
            for (int i = 0 ; i < sheets.getNumberOfSheets() ; i++ ){
                XSSFSheet sheetAt = sheets.getSheetAt(i);
                //为空跳过
                if (sheetAt == null){
                    continue;
                }
                Map<String, List> rowList = new HashMap();
                for (int j = 0 ; j <= sheetAt.getLastRowNum() ; j++ ){
                    XSSFRow row = sheetAt.getRow(i);
                    //为空跳过
                    if (row == null){
                        continue;
                    }
                    ArrayList cellList = new ArrayList();
                    for (int k = 0 ; k < row.getLastCellNum() ; k++ ){
                        XSSFCell cell = row.getCell(k);
                        cellList.add(ExcelUtils.getValue(cell,formulaEvaluator));
                    }
                    rowList.put("row" + j, cellList);
                }
                sheetMap.put("sheet" + i,rowList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                sheets.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sheetMap;
    }

    private static Map parseHSSF(InputStream is){
        HSSFWorkbook sheets = null;
        FormulaEvaluator formulaEvaluator = null;
        Map<String, Map> sheetMap = null;
        try {
            sheets = new HSSFWorkbook(is);
            formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) sheets);
            sheetMap = new HashMap();
            for (int i = 0 ; i < sheets.getNumberOfSheets() ; i++ ){
                HSSFSheet sheetAt = sheets.getSheetAt(i);
                if (sheetAt == null){
                    continue;
                }
                Map<String, List> rowList = new HashMap();
                for (int j = 0 ; j <= sheetAt.getLastRowNum() ; j++ ){
                    HSSFRow row = sheetAt.getRow(i);
                    if (row == null){
                        continue;
                    }
                    ArrayList cellList = new ArrayList();
                    for (int k = 0 ; k < row.getLastCellNum() ; k++ ){
                        HSSFCell cell = row.getCell(k);
                        cellList.add(ExcelUtils.getValue(cell,formulaEvaluator));
                    }
                    rowList.put("row" + j, cellList);
                }
                sheetMap.put("sheet" + i,rowList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                sheets.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sheetMap;
    }

    public static Map parse(MultipartFile multipartFile) throws Exception {
        //辨别后缀
        if (multipartFile.getOriginalFilename().endsWith(xlsxSuffix)){
            return parseXSSF(multipartFile.getInputStream());
        }else if (multipartFile.getOriginalFilename().endsWith(xlsSuffix)){
            return parseHSSF(multipartFile.getInputStream());
        }else{
            return null;
        }
    }
    public static void main(String[] args) {

    }
}
