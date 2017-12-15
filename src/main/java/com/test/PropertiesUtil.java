package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static final Properties prop = getProperties();
    public static final String PROP_PATH = "./src/main/java/com/test/properties/prop.properties";
    public static final String DB_PATH = "./src/main/java/com/test/properties/db.properties";
    public static final String CLAIM_XML_PATH = prop.getProperty("claimXMLPath");
    public static final String ESTIMATE_CLAIM_XML_PATH = prop.getProperty("estimateClaimXMLPath");

    public static final String PUSHCLAIM_URL = prop.getProperty("pushClaimUrl");
    public static final String ESTIMATE_PUSHCLAIM_URL = prop.getProperty("estimate_pushClaimUrl");

    public static final String CHROME_DRIVER_PATH = prop.getProperty("chromeDriverPath");


    public static final String URL = prop.getProperty("estimate_url");
    public static final String DATA_PATH = prop.getProperty("dataPath");
    public static final String ESTIMATE_EXCEL_SHEET_NAME = prop.getProperty("estimate_excelSheetName");
    public static final String CANCLERULE_SHEET_NAME = prop.getProperty("cancleRule_excelSheetName");
    public static final String CHANDAO_EXCEL_SHEET_NAME = prop.getProperty("chandao_excelSheetName");

    public static final String CACHE_7006_PATH = prop.getProperty("Cache_7006_Path");

    public static final String filepath=System.getProperty("user.dir")+ File.separator+"testdata"+File.separator;


//    public static final Configuration file = CommonsPropertiesUtils.getCommonsPropertis();
//    public static final String ip = file.getString("ip");




    public static final  int  SLEEP_TIME = 5000;
    public static final  int  LONG_SLEEP_TIME = 10000;

    public static Properties getProperties() {
        Properties pro = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(PROP_PATH);
            pro.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }
//    public static void main (String args[]){
//        System.out.println(ip);
//    }
}
