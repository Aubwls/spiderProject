package com.hwt.spider.spiderUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.text.SimpleDateFormat;

/**
 * @author: hwt
 * @date: 2020/11/4
 * @description:
 */
public class ExcelUtils {

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
}
