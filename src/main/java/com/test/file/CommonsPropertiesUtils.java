package com.test.file;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import java.util.Arrays;
import java.util.List;


public class CommonsPropertiesUtils {
    static  Configuration configuration;
    public static Configuration getCommonsPropertis() {
        try {
            configuration = new PropertiesConfiguration("config.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }
    public static void readProperiesFile(){

        try {
            //注意路径默认指向的是classpath的根目录
            configuration = new PropertiesConfiguration("config.properties");
            //对于一般属性直接获取就行
            String ip = configuration.getString("ip");
            int port = configuration.getInt("port");
            System.out.println(" ip="+ip+" port="+port);

            // //application.title属性的值是组合而成的,获取时可直接获取
            String title = configuration.getString("application.title");
            System.out.println(" title="+title );//title=Killer App 1.6.2

            //file.properties文件中如果不在存 id及对应的值,就自动为其添加及设置id及id值
            String id = configuration.getString("id","me is id");
            System.out.println(" id="+id );//id=me is id

           String[] keys =  configuration.getStringArray("keys");
           System.out.println(Arrays.toString(keys));//[cn, com, org, uk, edu, jp, hk]

           List<Object> list = configuration.getList("keys");
           for(Object ls : list){
               System.out.print("\t"+ls.toString());//cn  com	org	uk	edu	jp	hk
           }
           System.out.println(Arrays.toString(list.toArray()));//[cn, com, org, uk, edu, jp, hk]

            //因为Configuration 默认是以 逗号 分隔字符串，而config.properties文件中 con的值中是以”-“连接的，所以需要修改Configuration的默认分隔字符串 为”-“
            AbstractConfiguration.setDefaultListDelimiter('-');
            //修改默认分隔字符串 为”-“后，需要重新加载config.properties文件
            Configuration config = new PropertiesConfiguration("config.properties");
            //通过Configuration的getList方法获取以”-“分隔后的con值，返回类型为List
            List<Object> conlist = config.getList("con");
            //conlist.toArray() 将conlist转化为一个对象，然后Arrays.toString()将数组打印出来
            String str = Arrays.toString(conlist.toArray());
            System.out.println(" str="+str );//str=[cn, com, org, uk, edu, jp, hk]

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void main (String args[]){
        CommonsPropertiesUtils.readProperiesFile();
    }
}
