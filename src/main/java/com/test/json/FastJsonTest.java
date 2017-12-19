package com.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * Created by Suncy on 2017/12/17 0017.
 */
public class FastJsonTest {
    public static void main(String[] args) {
        //普通map
        String jsonString1 = "{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"}";
        System.out.println(jsonString1);
        Map<String,String> stringStringMap = (Map<String,String>) JSON.parse(jsonString1);
        for (String s : stringStringMap.keySet()) {
            System.out.println(s + "==>" +stringStringMap.get(s));
        }
        System.out.println("===================================================");


        //List<Map<String,String>>
        String jsonString2 = "[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]";
        System.out.println(jsonString2);
        List<Map<String,String>> mapList = JSON.parseObject(jsonString2, new TypeReference<List<Map<String,String>>>(){});
        for (Map<String, String> stringObjectMap : mapList) {
            for (String s : stringObjectMap.keySet()) {
                System.out.println(s + "==>" + stringObjectMap.get(s));
            }
        }
        System.out.println("===================================================");

        //Map<String,Object> ==> Object还能够进行分解
        String jsonString3 = "{\"count\":2,\"list\":[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]}";
        System.out.println(jsonString3);
        Map<String,Object> map = JSON.parseObject(jsonString3);
        System.out.println(map.get("count"));
        String tempjsonString3 = map.get("list").toString();
        System.out.println(tempjsonString3);
        List<Map<String,String>> mapList2 = JSON.parseObject(tempjsonString3, new TypeReference<List<Map<String,String>>>(){});
        for (Map<String, String> stringObjectMap : mapList2) {
            for (String s : stringObjectMap.keySet()) {
                System.out.println(s + "==>" + stringObjectMap.get(s));
            }
        }
        System.out.println("===================================================");

        //解析已有的对象
        String jsonString4 = "[{\"age\":12,\"date\":1465475917155,\"name\":\"s1\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s2\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s3\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s4\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s5\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s6\"}]";
        System.out.println(jsonString4);
        List<Student> studentList = JSON.parseArray(jsonString4,Student.class);
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("===================================================");

        //解析已有的对象的另一种方式
        System.out.println(jsonString4);
        List<Student> studentList2 = JSON.parseObject(jsonString4,new TypeReference<List<Student>>(){});
        for (Student student : studentList2) {
            System.out.println(student);
        }
    }
}
