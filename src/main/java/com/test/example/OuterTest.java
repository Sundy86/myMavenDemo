package com.test.example;

import java.util.Iterator;
import java.util.Map;

import static com.test.jdbc.DBTest.getSingleResultSet;

public class OuterTest {
    public int sumNum(int a, int b)
    {
        return a + b;
    }
    public String getVtm(){
        String vin = "LBFHDAFB6BY669414";
        String vin10 = vin.substring(9,10);
        String  year="";

        if (!vin10.equals("0")) {
            String sql = "SELECT T.CODE_DESC FROM T_PUB_CODE T WHERE T.CODE_TYPE_ID = 'PCD1280' AND T.CODE_VALUE = '"+vin10+"'";
            Map sql_result=getSingleResultSet(sql);
            if (!(sql_result == null || sql_result.size() == 0)) {
                Iterator<Map.Entry<String, Object>> it = sql_result.entrySet().iterator();
                Map.Entry<String, Object> e = it.next();
                year = e.getValue().toString();
            }
        } else {
            System.out.println("VIN码第10位为0，不进行年份校验");
            year = "na";
        }
        return year;
    }
    public static void main(String agrs[]){

        // 外层循环，outer作为标识符
        Outer:for(int i=0 ;i<=3;i++){
            // 内层循环
            for(int j=0;j<=3;j++){
                System.out.println("j is :"+j+" and i is:"+i);
                if(j==2){
                    // 结束outer标签所标识的单次循环
                    continue Outer;
                }
                if(j==5){
                    // 结束outer标签所标识的整个循环
                    break  Outer;
                }

            }
        }

    }
}
