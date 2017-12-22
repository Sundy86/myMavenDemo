package com.test.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.test.http.HttpUtils.doGet;
import static com.test.http.HttpUtils.doPost;

public class HttpUtilsTest {
    public static void main(String[] args) {
        doPostJsonTest();
    }
    public static void doGetTest(){
        String result = null;
        String url ="http://123.58.251.183:8080/goods/json2";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("count", 2);
        String jsonParam = JSON.toJSONString(map);

        Map<String, Object> header = new HashMap<String,Object>();
        header.put("token", "61b3590090982a0185dda9d3bd793b46");

        try {
            String reString = HttpUtils.doPostJson(url, jsonParam, header);
            System.out.println(reString);
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
        String url1 ="http://api.superepc.com/vtm/DataFunc?grant_code=XF9JKY0R&is_car_config=0&isCN=1&vinCode=LGBG22E22AY081092";
        try {
            String  result1 = doGet(url1);
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

    public static void doPostJsonTest(){
        String url ="http://123.58.251.183:8080/goods/json2";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("count", 2);
        String jsonParam = JSON.toJSONString(map);
        Map<String, Object> header = new HashMap<String,Object>();
        header.put("token", "61b3590090982a0185dda9d3bd793b46");
        try {
            String reString = HttpUtils.doPostJson(url, jsonParam, header);
            System.out.println(reString);
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
    }
    public static void doUploadTest(){
        String url="http://123.58.251.183:8080/FileSever/upload.do";
        String filePath = System.getProperty("user.dir")+ File.separator+"testdata"+File.separator;
        File file = new File(filePath+"test1.xml");
        String result = HttpUtils.doUpload(url,file);
        System.out.println(result);

        JSONObject  object =JSON.parseObject(result);
        String fileId = object.getString("fileId");
        try {
          String result2 =  HttpUtils.doGet("http://123.58.251.183:8080/FileSever/url.do?fileId="+fileId);
          JSONObject object1 = JSON.parseObject(result2);
          String downloadUrl =  object1.getString("url");
          File file1 = new File(filePath+"test2.xml");
          HttpUtils.doDownload(downloadUrl,file1);
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
    }
}
