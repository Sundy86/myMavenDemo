package com.test.data2;

import org.testng.annotations.Test;

public class ReadExcelForDataProvider {
    @Test(dataProvider = "excledata" , dataProviderClass = DataProviderForExcel.class)
    public void testMain(String policyNO,String surveyName,String insuredName){
        System.out.println(policyNO+"----------------"+surveyName+"----------------"+ insuredName );
    }
}
