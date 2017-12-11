package com.test.example;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import static com.test.jdbc.DBTest.getSingleResultSet;

public class VtmTest {
     String year;
     List list = new ArrayList();
     public String vtm(){
         String vin10 = "LBFHDAFB6BY669414".substring(9,10);
         String csv ;
         if (!vin10.equals("0")) {
             String sql = "SELECT T.CODE_DESC FROM T_PUB_CODE T WHERE T.CODE_TYPE_ID = 'PCD1280' AND T.CODE_VALUE = '"+vin10+"'";
             Map  sql_result;
             sql_result = getSingleResultSet(sql);
             if (!(sql_result == null || sql_result.size() == 0)) {
                 Iterator<Map.Entry<String, Object>> it = sql_result.entrySet().iterator();
                 Map.Entry<String, Object> e = it.next();
                 year = e.getValue().toString();
             }
                System.out.println("该VIN码对应的生产年份为：" + year + " 年");
         } else {
             System.out.println("VIN码第10位为0，不进行年份校验");
             year = "na";
         }
         return year;
     }

    public  String getVtmResult(String vin){
        //String vin ="LBFHDAFB6BY669414";
        String vin10 = vin.substring(9,10);
        String csv ;
        if (!vin10.equals("0")) {
            String sql = "SELECT T.CODE_DESC FROM T_PUB_CODE T WHERE T.CODE_TYPE_ID = 'PCD1280' AND T.CODE_VALUE = '"+vin10+"'";
            Map  sql_result;
            sql_result = getSingleResultSet(sql);
             if (!(sql_result == null || sql_result.size() == 0)) {
                Iterator<Map.Entry<String, Object>> it = sql_result.entrySet().iterator();
                Map.Entry<String, Object> e = it.next();
                year = e.getValue().toString();
            }
            System.out.println("该VIN码对应的生产年份为：" + year + " 年");
         } else {
            System.out.println("VIN码第10位为0，不进行年份校验");
             year = "na";
        }
        String sturl ="http://api.superepc.com/vtm/DataFunc?grant_code=test&is_car_config=0&isCN=1&vinCode="+vin;
        JSONArray array =httpRequest(sturl,"GET");
        int   retCode =  array.getJSONObject(0).getInt("retCode");
        System.out.println("VTM2.0接口返回的retCode为："+retCode);
        Map resultSet;

        if (0 < retCode && retCode <51) {    //获取retCode,TID,vhlSubModelId
            System.out.println("VTM2.0接口返回到款型");
            System.out.println(year+"   "+array.size());
            for (int i = 0; i < array.size(); i++) {    //循环处理VTM2.0接口返回记录中的每个TID
                JSONObject item = array.getJSONObject(i);
                String tid = item.get("TID").toString();
                System.out.println("当前处理的TID为：" + tid);
                if(!vin10.equals("0")){
                    String sql_1 = "SELECT t.TIMING_ID,t.VEHICLE_SUB_MODEL_ID,t.BEGIN_YEAR,t.END_YEAR FROM t_md_vehicle_model t WHERE t.TIMING_ID = '" +tid+ "' AND ((t.BEGIN_YEAR <= '" +year+ "' and t.END_YEAR is null) or (t.BEGIN_YEAR is null and t.END_YEAR >= '" +year+ "') or (t.BEGIN_YEAR <= '" +year+ "' and t.END_YEAR >= '" +year+ "'))";
                    resultSet = getSingleResultSet(sql_1);
                }else{
                    String sql_2 = "SELECT t.VEHICLE_SUB_MODEL_ID,t.TIMING_ID,t.BEGIN_YEAR,t.END_YEAR FROM t_md_vehicle_model t WHERE t.TIMING_ID = '" +tid+ "'";
                    resultSet = getSingleResultSet(sql_2);
                }
                if (!(resultSet == null || resultSet.size() == 0)) {
                     Iterator<Map.Entry<String, Object>> it = resultSet.entrySet().iterator();
                    if(it.hasNext()){//循环获取一个TID对应的每个vhlSubModelId
                        System.out.println("保存符合条件的记录");
                         while (it.hasNext()){
                           Map.Entry  e = it.next();
                            if(e.getKey().equals("VEHICLE_SUB_MODEL_ID")){
                                System.out.println(e.getKey()+"--"+e.getValue());
                                list.add(e.getValue());
                                break;
                            }
                        }
                    }else{  System.out.println(" :( 没有符合条件的记录"); }
                }
            }
            System.out.println("VMI返回的有效款型数量为：" + String.valueOf(list.size()));
            if(list.size()!=0){
                System.out.println("VMI组件返回到款型，有效的vhlSubModelId如下：");
            }else{
                System.out.println(" :( VMI组件没有返回有效记录，返回“-3”。");
            }
            StringBuilder csvBuilder = new StringBuilder();
            for(Object n:list){
                csvBuilder.append(n);
                csvBuilder.append(",");
            }
            csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);
            System.out.println(csv+"\n");
        }
        else if (retCode == 501 || retCode == 2001){	//获取retCode，厂商ID，品牌ID
            System.out.println("VTM2.0接口返回到车系");
            for(int i = 0; i < array.size(); i++){	//循环处理VTM2.0接口返回记录中的每组厂商ID和品牌ID
                JSONObject item =  array.getJSONObject(i);
                String mf_id = item.get("厂商ID").toString();
                String brand_id = item.get("品牌ID").toString();
                System.out.println("当前处理的厂商ID为：" + mf_id + ", 品牌ID为：" + brand_id);
                String sql = "SELECT t.TIMING_MF_ID,t.TIMING_BRAND_ID,t.COUNTRY_ID,t.VEHICLE_MAKE_ID,t.VEHICLE_MANUF_MAKE_NAME FROM t_md_vehicle_make t WHERE t.TIMING_MF_ID = '" +mf_id+ "' and t.TIMING_BRAND_ID = '" +brand_id+ "'";
                System.out.println(sql);
                resultSet = getSingleResultSet(sql);
                if (!(resultSet == null || resultSet.size() == 0)) {
                    Iterator<Map.Entry<String, Object>> it = resultSet.entrySet().iterator();
                    if(it.hasNext()){//循环获取一个TID对应的每个vhlSubModelId
                        System.out.println("保存符合条件的记录");
                        while (it.hasNext()){
                            Map.Entry<String, Object> e = it.next();
                            System.out.println(e.getKey()+"--"+e.getValue());
                            list.add(e.getValue());
                        }
                    }else{  System.out.println(" :( 没有符合条件的记录"); }
                }
            }
            if(list.size()!=0){
                System.out.println("VMI组件返回到车系，有效的国别和CCC厂牌如下：");
            }else if(list.size() == 0 && retCode == 501){
                System.out.println(" :( VMI组件没有返回有效记录，返回“-5”。");
            }else{
                System.out.println(" :( VMI组件没有返回有效记录，返回“-20”。");
            }
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
            csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);
            System.out.println(csv+"\n");
        }
        else if(501 < retCode && retCode < 551){
            System.out.println("VTM2.0接口返回到车系，且有多条记录，直接舍弃不处理");
            System.out.println(year);
            System.out.println(" :( VMI组件没有返回有效记录，返回“-20”。");
            list.add("-5");
            list.add(year);
            list.add("VMI组件没有返回有效记录");
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
             csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);
            System.out.println(csv+"\n");
        }
        else if(2001 < retCode && retCode < 2051){
            System.out.println("VTM2.0接口返回到车系，且有多条记录，直接舍弃不处理");
            System.out.println(year);
            System.out.println(" :( VMI组件没有返回有效记录，返回“-20”。");
            list.add("-20");
            list.add(year);
            list.add("VMI组件没有返回有效记录");
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
             csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);
            System.out.println(csv+"\n");
        }
        else if (retCode == 801){	//获取retCode，厂商ID，品牌ID
            System.out.println("VTM2.O接口返回到厂牌");
            System.out.println(year);
            for(int i = 0; i < array.size(); i++){	//循环处理VTM2.0接口返回记录中的每组厂商ID和品牌ID
                JSONObject item = array.getJSONObject(i);
                String mf_id = item.get("厂商ID").toString();
                String brand_id = item.get("品牌ID").toString();
                System.out.println("当前处理的厂商ID为：" + mf_id + ", 品牌ID为：" + brand_id);
                String sql = "SELECT t.TIMING_MF_ID,t.TIMING_BRAND_ID,t.COUNTRY_ID,t.VEHICLE_MAKE_ID,t.VEHICLE_MANUF_MAKE_NAME FROM t_md_vehicle_make t WHERE t.TIMING_MF_ID = '" +mf_id+ "' and t.TIMING_BRAND_ID = '" +brand_id+ "'";
                resultSet = getSingleResultSet(sql);
                System.out.println(sql);

                System.out.println("匹配到的国别、厂牌组数为：" +String.valueOf(array.size()));
                if (!(resultSet == null || resultSet.size() == 0)){
                  //获取该组厂商/品牌ID对应的国别和CCC厂牌
                    System.out.println("保存符合条件的记录");
                    Iterator<Map.Entry<String, Object>> it = resultSet.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry<String, Object> e = it.next();
                        System.out.println(e.getKey() + "--" + e.getValue());
                        list.add(e.getValue());
                    }
                }else{ System.out.println(" :( 没有符合条件的记录"); }
            }
            if(list.size()!=0){
                System.out.println("VMI组件返回到车系，有效的国别和CCC厂牌如下：");
                list.add(0,"1");
                list.add(1,"VMI2.0接口定型到车系,VTM组件返回厂牌");
            }else{
                System.out.println(" :( VMI组件没有返回有效记录，返回“-8”。");
                list.add("-8");
                list.add(year);
                list.add("VMI组件没有返回有效记录");
            }
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
             csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);

            System.out.println(csv+"\n");
        }
        else if(801 < retCode && retCode < 851){
            System.out.println("VTM2.0接口返回到厂牌，且有多条记录，直接舍弃不处理");
            System.out.println(year);
            System.out.println(" :( VMI组件没有返回有效记录，返回“-8”。");
            list.add("-8");
            list.add(year);
            list.add("VMI组件没有返回有效记录");
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
             csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);

            System.out.println(csv+"\n");
        }
        else {	//获取retCode
            System.out.println("VTM2.0返回异常");
            System.out.println(year);
            list.add("-90");
            list.add(year);
            list.add(array);
            StringBuilder csvBuilder = new StringBuilder();
            for(Object i:list){
                csvBuilder.append(i);
                csvBuilder.append(",");
            }
             csv = csvBuilder.toString();	//保存VMI组件结果，供后续比较使用
            csv = csv.substring(0,csv.length()-1);

            System.out.println(csv+"\n");
        }
        return csv;
    }


    public static JSONArray httpRequest(String requestUrl, String requestMethod) {
        JSONArray arrays=null;
        StringBuffer buffer = new StringBuffer();
        try {

            URL url = new URL(requestUrl);
            // http协议传输
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            String strr = buffer.substring(9,buffer.length()-1).toString();
            System.out.println(strr);
             arrays =  JSONArray.fromObject(strr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrays;
    }

        public static void main(String[] args) {
           new VtmTest().getVtmResult("LBFHDAFB6BY669414");
            new VtmTest().vtm();
        }

}
