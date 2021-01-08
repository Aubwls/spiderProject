package com.hwt.spider.spiderUtils;
import com.hwt.spider.annotation.ColumnName;
import lombok.Data;
import lombok.val;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: hwt
 * @date: 2020/11/4
 * @description:
 */
public class ParseExcel {

    private static Map<Integer, Map<String, Integer>> tableHead = null;

    private static final String XLSX_SUFFIX = "xlsx";

    private static final String XLS_SUFFIX = "xls";

    private static final String SET_PREFIX = "set";

    private static Map<Integer, Map<Integer, List<Object>>> parseXSSF(InputStream is){
        XSSFWorkbook sheets = null;
        FormulaEvaluator formulaEvaluator = null;
        Map<Integer, Map<Integer, List<Object>>> sheetMap = null;
        try {
            sheets = new XSSFWorkbook(is);
            formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) sheets);
            sheetMap = new HashMap();
            Map<String, Integer> columnNameMap = null;
            for (int i = 0 ; i < sheets.getNumberOfSheets() ; i++ ){
                XSSFSheet sheetAt = sheets.getSheetAt(i);
                //为空跳过
                if (sheetAt == null){
                    continue;
                }
                Map<Integer, List<Object>> rowList = new HashMap();
                columnNameMap = new HashMap();
                //获取表头数据
                XSSFRow headRow = sheetAt.getRow(0);
                for (int a = 0 ; a < headRow.getLastCellNum() ; a++){
                    columnNameMap.put(getValue(headRow.getCell(a),formulaEvaluator), a);
                }
                tableHead.put(i, columnNameMap);
                for (int j = 1 ; j <= sheetAt.getLastRowNum() ; j++ ){
                    XSSFRow row = sheetAt.getRow(j);
                    //为空跳过
                    if (row == null){
                        continue;
                    }
                    List<Object> cellList = new ArrayList(row.getLastCellNum());
                    for (int k = 0 ; k < row.getLastCellNum() ; k++ ){
                        XSSFCell cell = row.getCell(k);
                        cellList.add(getValue(cell,formulaEvaluator));
                    }
                    rowList.put(j, cellList);
                }
                sheetMap.put(i,rowList);
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

    private static Map<Integer, Map<Integer, List<Object>>> parseHSSF(InputStream is){
        HSSFWorkbook sheets = null;
        FormulaEvaluator formulaEvaluator = null;
        Map<Integer, Map<Integer, List<Object>>> sheetMap = null;
        try {
            sheets = new HSSFWorkbook(is);
            formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) sheets);
            sheetMap = new HashMap();
            Map<String, Integer> columnNameMap = null;
            for (int i = 0 ; i < sheets.getNumberOfSheets() ; i++ ){
                HSSFSheet sheetAt = sheets.getSheetAt(i);
                if (sheetAt == null){
                    continue;
                }
                HSSFRow headRow = sheetAt.getRow(0);
                for (int a = 0 ; a < headRow.getLastCellNum() ; a++){
                    columnNameMap.put(getValue(headRow.getCell(a),formulaEvaluator), a);
                }
                tableHead.put(i, columnNameMap);
                Map<Integer, List<Object>> rowList = new HashMap();
                for (int j = 1 ; j <= sheetAt.getLastRowNum() ; j++ ){
                    HSSFRow row = sheetAt.getRow(j);
                    if (row == null){
                        continue;
                    }
                    List<Object> cellList = new ArrayList();
                    for (int k = 0 ; k < row.getLastCellNum() ; k++ ){
                        HSSFCell cell = row.getCell(k);
                        cellList.add(getValue(cell,formulaEvaluator));
                    }
                    rowList.put(j , cellList);
                }
                sheetMap.put(i ,rowList);
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

    public static String getValue(Cell cell, FormulaEvaluator formulaEvaluator){
        switch (cell.getCellType())
        {
            case STRING: return cell.getStringCellValue();
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    short format =  cell.getCellStyle().getDataFormat();
                    SimpleDateFormat sdf = null;
                    /* 所有日期格式都可以通过getDataFormat()值来判断
                     *     yyyy-MM-dd----- 14
                     *    yyyy年m月d日----- 31
                     *    yyyy年m月--------57
                     *    m月d日  --------- 58
                     *    HH:mm---------- 20
                     *    h时mm分  --------- 32
                     */
                    if(format == 14 || format == 31 || format ==  57 || format == 58){
                        //日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }else if (format == 20 || format == 32) {
                        //时间
                        sdf = new SimpleDateFormat("HH:mm");
                    }
                    return sdf.format(cell.getDateCellValue());
                }else {
                    // 对整数进行判断处理
                    double cur = cell.getNumericCellValue();
                    long longVal = Math.round(cur);
                    Object inputValue = null;
                    if(Double.parseDouble(longVal + ".0") == cur)  {
                        inputValue = longVal;
                    }
                    else {
                        inputValue = cur;
                    }
                    return String.valueOf(inputValue);
                }
                //对公式进行处理,返回公式计算后的值,使用cell.getCellFormula()只会返回公式
            case FORMULA: return  String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
            //Cell.CELL_TYPE_BLANK || Cell.CELL_TYPE_ERROR
            default: return null;
        }
    }
    private static List<Object> transformObject(Map<Integer, List<Object>> map, Class clazz, Map<String, Integer> tableHeadMap){
        List<Object> objectList = new ArrayList(map.keySet().size());
        try {
            for (Map.Entry<Integer, List<Object>> entry : map.entrySet()) {
                Object o = clazz.newInstance();
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    String desc = field.getAnnotation(ColumnName.class).desc();
                    String method = SET_PREFIX + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
                    clazz.getMethod(method, field.getType()).invoke(o,  entry.getValue().get(tableHeadMap.get(desc)));
                }
                objectList.add(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectList;
    }
    public static Map<Integer, List<Object>> parse(File file, Class clazz) throws Exception {
        //辨别后缀
        Map<Integer, Map<Integer, List<Object>>> map = null;
        Map<Integer, List<Object>> result = new LinkedHashMap();
        tableHead = new HashMap();
//        if (multipartFile.getOriginalFilename().endsWith(XLSX_SUFFIX)){
//            map = parseXSSF(multipartFile.getInputStream());
//        }else if (multipartFile.getOriginalFilename().endsWith(XLS_SUFFIX)){
//            map = parseXSSF(multipartFile.getInputStream());
//        }else{
//            return null;
//        }
        map = parseXSSF(new FileInputStream(file));
        for (Map.Entry<Integer, Map<Integer, List<Object>>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            List<Object> list = transformObject(entry.getValue(), clazz, tableHead.get(entry.getKey()));
            result.put(key, list);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Map<Integer, List<Object>> parse = parse(new File("C:\\Users\\Administrator\\Desktop\\test.xlsx"), product.class);
        System.out.println(parse);
    }
}
class product{

    @ColumnName(desc = "产品")
    private String product;

    @ColumnName(desc = "版本")
    private String version;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "product{" +
                "product='" + product + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}