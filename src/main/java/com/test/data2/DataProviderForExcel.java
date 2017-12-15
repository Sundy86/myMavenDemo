package com.test.data2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import static com.test.PropertiesUtil.*;

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

    @DataProvider(name="searchData")
    public static Object[][] data() throws IOException
    {
        //获取Excel文件的测试数据
        return new ExcelData().getData(filepath, "testData.xlsx","ruleNo");
    }
}
