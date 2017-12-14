package com.test.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class IOUtilsTest {
    public static void main(String[] args) {
        //download();
        showinfo();
    }
    //String IOUtils.toString(InputStream input),传入输入流对象，返回字符串
    public static void showinfo(){
        InputStream in =null;
        try {
            URL url = new URL("http://192.168.200.14:7001/web-suite/");
           in =url.openStream();
            String s = IOUtils.toString(in);
            System.out.println(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //利用IOUtils.toByteArray(InputStream input)及FileUtils.writeByteArrayToFile(File file, byte[] data)下载文件到本地
    public static void download(){
        try {
            InputStream inputStream = new URL("https://www.baidu.com/img/baidu_logo.gif").openStream();
            byte[] gif =  IOUtils.toByteArray(inputStream);
            // 清空目的文件，然后再粘贴内容
            FileUtils.writeByteArrayToFile(new File("D:/baidu.gif"),gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
