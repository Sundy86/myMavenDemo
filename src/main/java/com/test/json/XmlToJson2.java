package com.test.json;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XmlToJson2 {
    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"pushClaim.xml";
        String jsonpath =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"test2.json";
        System.out.println(path);
        String json =  XmlConversJson(path);

        FileUtils.writeStringToFile(new File(jsonpath),json,"utf-8");
    }
    public static String XmlConversJson(String  filepath) throws IOException {
        FileInputStream in = new FileInputStream(new File(filepath)) ;
        String xml = IOUtils.toString(in,"utf-8");
        JSONObject object =  XML.toJSONObject(xml);
        return  object.toString();
    }


}
