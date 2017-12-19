package com.test.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.protocol.HTTP;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.test.http.HttpUtils.doGet;
import static com.test.http.HttpUtils.doPost;
import static com.test.http.HttpUtils.doPostJson;

public class HttpUtilsTest {
    public static void main(String[] args) {
        doPostTest();
    }
    public static void doGetTest(){
        String result = null;
        String url ="http://api.superepc.com/vtm/DataFunc?grant_code=XF9JKY0R&is_car_config=0&isCN=1&vinCode=LGBG22E22AY081092";
        try {
            String  result1 = doGet(url);
            result=result1.substring(9,result1.length()-1);
            System.out.println(result);

            List<Map<String,String>> mapList = JSON.parseObject(result, new TypeReference<List<Map<String,String>>>(){});
            for (Map<String, String> stringObjectMap : mapList) {
                for (String s : stringObjectMap.keySet()) {
                    System.out.println(s + "==>" + stringObjectMap.get(s));
                }
                System.out.println(stringObjectMap.get("retCode"));
            }

        } catch (HttpClientException e) {
            e.printStackTrace();
        }
    }
    public static void doPostTest(){
        String result = null;
        String url ="http://api.superepc.com/vtm/DataFunc";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("grant_code","XF9JKY0R");
        map.put("is_car_config",0);
        map.put("isCN",1);
        map.put("vinCode","LGBG22E22AY081092");


        try {

            result = doPost(url,map);
            System.out.println(result);
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
    }

}
