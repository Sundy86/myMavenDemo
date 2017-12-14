package com.test.file;

import org.apache.commons.io.IOUtils;
import java.io.*;

public class IOUtilsDownloadFile {
    public static void main(String[] args) {
      String path = "c:/1.txt";
      byte[] bytes =getFileData(path);
      if(null == bytes){
          try {
              throw new FileNotFoundException("文件没有找到！");
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
      }
      String filepath = "d:/1.txt";
      creatNewFile(filepath,bytes);

    }
    public static void creatNewFile(String filePath,byte[]data){
        File file = new File(filePath);
        OutputStream outputStream = null;
        try {
        if(!file.exists()) {
            file.createNewFile();
        }
          outputStream = new FileOutputStream(filePath);
          IOUtils.write(data,outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream=null;
        }
    }

    public static  byte[]  getFileData(String filePath)   {
        File file = new File(filePath);
        byte[] bytes=null;
        InputStream inputStream =null;
        try {
            if(!file.exists()) {
                return null;
            }
            inputStream = new FileInputStream(file);
            bytes =IOUtils.toByteArray(inputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            inputStream=null;
        }
        return bytes ;
    }
}
