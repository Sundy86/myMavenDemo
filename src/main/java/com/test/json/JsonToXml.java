package com.test.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;

public class JsonToXml {

    private static XMLSerializer xmlserializer = new XMLSerializer();

    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test2.json";
        String json = FileUtils.readFileToString(new File(path),"utf-8");
        String xml = json2xml(json);
        String xmlpath =System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test.xml";
        FileUtils.writeStringToFile(new File(xmlpath),xml,"utf-8");
    }
    public static String json2xml(String jsonStr){
        if(StringUtils.isNotBlank(jsonStr)){
            try {
                if(jsonStr.contains("[{") && jsonStr.contains("}]")){
                    JSONArray jobj = JSONArray.fromObject(jsonStr);
                    xmlserializer.setTypeHintsEnabled(false);
                    xmlserializer.setSkipNamespaces(true);
                    xmlserializer.setObjectName("");
                    xmlserializer.setElementName("");
                    return xmlserializer.write(JSONSerializer.toJSON(jobj));
                }
                JSONObject jobj = JSONObject.fromObject(jsonStr);
                xmlserializer.setTypeHintsEnabled(false);
                xmlserializer.setSkipNamespaces(true);

                return xmlserializer.write(JSONSerializer.toJSON(jobj));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
