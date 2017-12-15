package com.test.rulecancle;

import com.test.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RuleSetPageElement {
    WebDriver driver;
    Log log ;

    public static  final By ruleMenu = By.ByXPath("//li[@role='menuitem']/a[contains(text(),'规则')]");
     @FindBy(xpath = "//li[@role='menuitem']/a[contains(text(),'规则')]")
    WebElement ruleMenu;

    @FindBy(xpath = "//li/a[text()='规则管理']")
    WebElement ruleConfigMenu;

    @FindBy(xpath = "//input[@id='ruleNo']")
    WebElement ruleNo;

    @FindBy(xpath = "//div[@id='btnSearch']/span[text()='查 询']")
    WebElement ruleSearchBtn;

    @FindBy(xpath = "//a[@class='openRule' and @ruleno='0101010001']")
    WebElement openRuleLink;

    @FindBy(xpath = "//tr[@id='ruleManagement']/td/span[contains(@class,'lableRadio')]/input[@id='disabled']")
    WebElement disableApplyRadio;

    @FindBy(xpath = "//div[@id='ruleConfigSave']/span[text()='保 存']")
    WebElement ruleConfigSaveBtn;

    @FindBy(xpath = "//div[contains(@class,'popBtn')]/span[text()='确定']")
    WebElement OkBtn;

    @FindBy(xpath = "//div[contains(@class,'popBtn')]/span[text()='确定']")
    WebElement confirmBtn;

    public  RuleSetPageElement(WebDriver driver,Log log){
        this.driver = driver;
        this.log = log;
        PageFactory.initElements(driver,this);
    }

    public void setRuleNo(String no) {
        log.info("输入规则编号："+ no);
        ruleNo.clear();
        ruleNo.sendKeys(no);

    }
    public void openRuleList(){
        log.info("点击规则管理菜单");
        ruleMenu.click();
    }
    public void openRuleSetWindow(String ruleno) throws InterruptedException {
        log.info(driver.getCurrentUrl());
        this.setRuleNo(ruleno);
        log.info("点击查询按钮");
        ruleSearchBtn.click();
        Thread.sleep(5000);
        log.info("点击规则编号链接");
        String openRuleLinkXPATH = "//a[@class='openRule' and @ruleno='"+ruleno+"']";
        driver.findElement(By.xpath(openRuleLinkXPATH)).click();
        Thread.sleep(5000);

    }
    public void setRuleDisable() throws InterruptedException {
        log.info("点击是否应用");
        disableApplyRadio.click();
        Thread.sleep(3000);
        log.info("点击保存");
        ruleConfigSaveBtn.click();
        OkBtn.click();
        Thread.sleep(3000);
        log.info("点击确定");
        confirmBtn.click();
    }

}
