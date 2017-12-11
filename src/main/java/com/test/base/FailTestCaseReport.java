package com.test.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FailTestCaseReport {
    public void catchExceptions(ITestResult result, WebDriver driver) {

        //System.out.println("result:" + result);
        //get method name of the failed testcase
        String methodName = result.getName();
        //System.out.println("method Name:" + methodName);
        if (!result.isSuccess()) {

            //String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date());
            time = time + methodName;

            //当前项目中截图文件夹路径
            //String screenshotPath = SCREENSHOT_PATH;
            //System.out.println("Screenshot File Path=" + screenshotPath );
            String picPath = System.getProperty("user.dir").replace("\\","/") + "/screenshot/"+ time + ".png";
            String picUrl = "/screenshot/" + time + ".png";
            //System.out.println(picUrl);

            //System.out.println(picPath);
            try {
                File inFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                //创建流文件读入与写出类
                FileInputStream inStream = new FileInputStream(inFile);
                FileOutputStream outStream = new FileOutputStream(picPath);
                //通过available方法取得流的最大字符数
                byte[] inOutb = new byte[inStream.available()];
                inStream.read(inOutb);  //读入流,保存在byte数组
                outStream.write(inOutb);  //写出流,保存在文件newFace.gif中
                inStream.close();
                outStream.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            Reporter.setCurrentTestResult(result);
            Reporter.log(picPath.replace("/","\\"));
            //System.out.println("<img src='"+picUrl+"' onclick='window.open(\""+picUrl+"\")" + "' hight='200' width='200'/>");
            Reporter.log("<img src='"+picUrl+"' onclick='window.open(\""+picUrl+"\")" + "' hight='200' width='200'/>");


        }

    }
}
