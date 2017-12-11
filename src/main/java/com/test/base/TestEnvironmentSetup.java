package com.test.base;

import com.test.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static com.test.PropertiesUtil.CHROME_DRIVER_PATH;


public class TestEnvironmentSetup {
     WebDriver chromeDriver ;
     Log log ;

    public  TestEnvironmentSetup(String testcaseName){
        this.log = new Log(testcaseName);
    }
    public WebDriver InitChromeDriver(){
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        chromeDriver = new ChromeDriver(options);

        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return chromeDriver;
    }
    public Log getLog(){
        return log;
    }
}
