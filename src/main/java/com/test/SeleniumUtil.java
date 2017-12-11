package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static com.test.PropertiesUtil.LONG_SLEEP_TIME;
import static com.test.PropertiesUtil.SLEEP_TIME;

public class SeleniumUtil {
    WebDriver driver;
    Log log;
    public SeleniumUtil(WebDriver driver,Log log){
        this.driver = driver;
        this.log = log;
    }
    public void clickBtn(By by){
        driver.findElement(by).click();
    }
    public  void SwitchToNewWindow(){
        Set<String> allWindowHandles = driver.getWindowHandles();
        String curWindowHandle = driver.getWindowHandle();
        log.info("当前窗口句柄：" + curWindowHandle);
        log.info("窗口数量" + allWindowHandles.size());
        for (String s : allWindowHandles) {
            if (s.equals(curWindowHandle)) {
                log.info("当前窗口，继续");
                continue;
            } else {
                log.info("跳转到新窗口：" + s);
                driver.switchTo().window(s);
            }
        }
    }
    public  void SwitchToNewWindow1(){
        Set<String> allWindowHandles = driver.getWindowHandles();
        String curWindowHandle = driver.getWindowHandle();
        log.info("当前窗口句柄：" + curWindowHandle);
        log.info("窗口数量" + allWindowHandles.size());
        for (String s : allWindowHandles) {
            if (s.equals(curWindowHandle)) {
                log.info("当前窗口，继续");
                driver.switchTo().window(s);
            }
        }
    }

    public void sleep(){
        try {
            log.info("等待"+SLEEP_TIME+"ms");
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void longSleep(){
        try {
            log.info("等待"+LONG_SLEEP_TIME+"ms");
            Thread.sleep(LONG_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
