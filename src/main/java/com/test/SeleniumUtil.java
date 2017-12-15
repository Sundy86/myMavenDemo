package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    /**
     * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
     * */
    public void waitForElementToLoad(int elementTimeOut, final By By) {
        log.info("开始查找元素[" + By + "]");
        try {
            (new WebDriverWait(driver, elementTimeOut)).until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            log.error("超时!! " + elementTimeOut + " 秒之后还没找到元素 [" + By + "]");
            Assert.fail("超时!! " + elementTimeOut + " 秒之后还没找到元素 [" + By + "]");

        }
        log.info("找到了元素 [" + By + "]");
    }
}
