package com.test.jsonpath;

import com.alibaba.fastjson.JSONPath;
import com.test.http.HttpClientException;
import com.test.http.HttpUtils;

public class MocoJsonTest {
    public static void main(String[] args) throws HttpClientException {
       String json = HttpUtils.doGet("http://192.168.100.63:12306/carTaskForRule");
       String s = (String) JSONPath.read(json,"$.accidentPolicies.accPolicyCoverages[0].coverageName");
        System.out.println(s);
        String s1 = (String) JSONPath.read(json,"$.carInfo.modelName");
        System.out.println(s1);


    }
}
