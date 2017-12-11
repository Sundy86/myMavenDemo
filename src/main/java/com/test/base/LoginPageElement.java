package com.test.base;

import com.test.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.test.PropertiesUtil.CACHE_7006_PATH;
import static com.test.PropertiesUtil.URL;

public class LoginPageElement {
    WebDriver driver;
    Log log ;
    @FindBy(xpath = "//input[@id='j_username']")
    WebElement username;
    @FindBy(xpath = "//input[@id='j_password']")
    WebElement password;
    @FindBy(xpath = "//input[@id='captchaStr']")
    WebElement captchaStr;
    @FindBy(xpath = "//a[@id='submitLogin']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@id='searchConditionDiv']//input[@id='accidentNo']")
    WebElement accidentForSearch;
    @FindBy(xpath = "//div[@class='submit_btn task_btnDiv']/div[@id='btnSearch']")
    WebElement btnForSearch;
    @FindBy(xpath = "//td[@columnfield='accidentNo']/a")
    WebElement accidentLink;
    @FindBy(xpath = "//div[@id='tabstrip']/ul/li[@id='claimVehicleInfo']/a[text()='车辆信息']")
    WebElement claimVehicleInfoTab;

    public  LoginPageElement(WebDriver driver,Log log){
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
    public void setCaptchaStr(String captcha){
        log.info("输入验证码："+ captcha);
        captchaStr.sendKeys(captcha);
    }
    public void clickSubmitBtn(){
        log.info("点击登录按钮");
        submitButton.click();
    }
    public void setAccidentNo(String accidentNo){
        log.info("输入要查询的事故号"+accidentNo);
        accidentForSearch.sendKeys(accidentNo);
    }
    public void clickSearchBtn(){
        log.info("点击查询按钮");
        btnForSearch.click();
    }
    public  void clickAccidentNoLink(){
        log.info("点击事故号链接");
        accidentLink.click();
    }
    public  void clickClaimVehicleInfoTab(){
        log.info("点击车辆信息tab");
        claimVehicleInfoTab.click();
    }
    public void loginToHomePage(String username,String password,String captcha)throws  InterruptedException{
        log.info("登录到定损列表页面");
        driver.get(URL);

        this.setUsername(username);
        this.setPassword(password);
        this.setCaptchaStr(captcha);
        this.clickSubmitBtn();
        Thread.sleep(10000);
    }
    public void loginToCachePage(String username,String password,String captcha)throws  InterruptedException{
        log.info("登录到清理缓存页面");
        driver.get(CACHE_7006_PATH);
        this.setUsername(username);
        this.setPassword(password);
        this.setCaptchaStr(captcha);
        this.clickSubmitBtn();
        Thread.sleep(10000);
    }
    public void clearCache(){
        driver.findElement(By.xpath("//td/a[contains(@href,'standPartCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'paintDiscountPartCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'stringResourceCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'mdLocalLaborHourCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'drivingTypeCodeCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'wsUserCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'standardPartAliasMappingCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'vehicleLevelParamDefCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'materialAlasNameCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'blackListCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'vehicleUsingTypeCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'resourceMappingCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'mdLaborRelationCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'testCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'sysRepairFactoryCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'sysCompanyCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'sysConfigItemCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'paintOverlapCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'vehicleLevelCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'materialLevelDataCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'mdInstanceLaborHourCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'mdPartLocationsDataCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'commonCascadeRelCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'dataMappingCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'capacityImageCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'standPartAliasNameCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'validationConfigCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'wholePaintLaborLevelDataCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'vehicleDefineVtmCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'mdModelLaborHourCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'codeTableCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'vehicleDataCache')]")).click();
        driver.findElement(By.xpath("//td/a[contains(@href,'auditRuleCache')]")).click();
    }
}
