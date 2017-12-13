package com.test.file;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesTestUtis {
    private static Properties properties = null;

    static {
        if(null == properties){
            properties = new Properties();
            InputStreamReader reader = null;
            try{
                reader = new InputStreamReader(PropertiesTestUtis.class.getClassLoader().getResourceAsStream("config.properties"),"utf-8");
                properties.load(reader);
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(reader != null){
                    reader = null;
                }
            }
        }
    }

    private PropertiesTestUtis(){}

    public static String getString(String key){
        return properties.getProperty(key);
    }
        public static void main (String args[]){
            String name = PropertiesTestUtis.getString("application.name");
            System.out.println(" name="+name );
            String title = PropertiesTestUtis.getString("application.title");
            System.out.println(" title="+title );
         }
}
