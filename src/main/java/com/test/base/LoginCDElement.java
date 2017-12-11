package com.test.base;

import com.test.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.test.PropertiesUtil.URL;

public class LoginCDElement {
    WebDriver driver;
    Log log ;
    @FindBy(xpath = "//input[@id='account']")
    WebElement username;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@id='submit']")
    WebElement submitButton;


    public LoginCDElement(WebDriver driver, Log log){
        this.driver = driver;
        this.log = log;
        PageFactory.initElements(driver,this);
    }

    public void setUsername(String user){
        log.info("输入用户名："+ user);
        username.sendKeys(user);
    }
    public void setPassword(String ps){
        log.info("输入密码：" + ps);
        password.sendKeys(ps);
    }

    public void clickSubmitBtn(){
        log.info("点击登录按钮");
        submitButton.click();
    }


    public void loginToHomePage(String username,String password)throws  InterruptedException{
        log.info("登录到CHANDAO页面");
        driver.get("http://127.0.0.1:81/zentao/my.html");

        this.setUsername(username);
        this.setPassword(password);
         this.clickSubmitBtn();
        Thread.sleep(5000);
    }
}
