package com.test.data2;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import static com.test.PropertiesUtil.CANCLERULE_SHEET_NAME;
import static com.test.PropertiesUtil.CHANDAO_EXCEL_SHEET_NAME;
import static com.test.PropertiesUtil.ESTIMATE_EXCEL_SHEET_NAME;

public class DataProviderForExcel {
    @DataProvider()
    public Object[][] getDataFromExcel(Method m)throws FileNotFoundException {
        String className = m.getDeclaringClass().getName();
        String methodName = m.getName();
        //用testng的执行文件名作为excle的文件名
        String testcasename = className.substring(className.lastIndexOf(".") + 1, className.length()) + ".xlsx";
        //参数1为excle的sheet名
        if (methodName.equals("estimateTest")) {
            return new ExcelData().getData(ESTIMATE_EXCEL_SHEET_NAME, testcasename);
        }
        if (methodName.equals("cancleRule")) {
            return new ExcelData().getData(CANCLERULE_SHEET_NAME, testcasename);
        }
        else {
            return new ExcelData().getData(CHANDAO_EXCEL_SHEET_NAME, testcasename);
        }
    }
}
