package com.test.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XmlToJson1 {
    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"pushClaim.xml";
        String jsonpath =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"test1.json";
        String json = XmlToJson(new File(path));
        System.out.println(json);
        FileUtils.writeStringToFile(new File(jsonpath),json,"utf-8");
    }
    public static String XmlToJson(File file){
        JSONObject object = new JSONObject();
        SAXBuilder builder = null;
        try {
            builder = new SAXBuilder();
            Document document = builder.build(file);
            Element element =document.getRootElement();
            object.put(element.getName(),IterateElement(element));
            return object.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    private static Map IterateElement(Element element){
        List node = element.getChildren();
        List list = null;
        Map map = new HashMap();
        for(int i=0;i<node.size();i++){
            list = new LinkedList();
            Element element1 = (Element) node.get(i);
            if(element1.getTextTrim().equals("")){
                if(element1.getChildren().size()==0){
                    continue;
                }
                if(map.containsKey(element1.getName())){
                   list = (List) map.get(element1.getName());
                }
                list.add(IterateElement(element1));
                map.put(element1.getName(),list);
            }else{
                if(map.containsKey(element1.getName())){
                    list = (List) map.get(element1.getName());
                }
                list.add(element1.getTextTrim());
                map.put(element1.getName(),list);
            }
        }
        return  map;
    }
}
