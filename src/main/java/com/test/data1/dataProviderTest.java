package com.test.data1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProviderTest{
    @DataProvider(name = "data")
    public Object[][] dataInfo(){
        Object[][] dataList = new Object[][]{{"AAA","123"},{"BBB","345"},{"CCC","678"}};
        return  dataList;
    }
    @Test (dataProvider="data")
    public void testMain(String str1,String str2){
        System.out.println("----------"+str1+"-"+str2);
        /*
        *   ----------AAA-123
            ----------BBB-345
            ----------CCC-678
        * */
    }
}
