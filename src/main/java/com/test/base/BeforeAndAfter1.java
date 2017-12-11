package com.test.base;

import com.test.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BeforeAndAfter1 {
    private TestEnvironmentSetup ts;
    protected WebDriver chrome_driver;
    protected Log log;
    private String testcaseName;
     public BeforeAndAfter1(){
         testcaseName = this.getClass().getName();
         ts = new TestEnvironmentSetup(testcaseName);
         this.log = ts.getLog();
         chrome_driver = ts.InitChromeDriver();
    }

    public void afterMethod(ITestResult result) throws Exception {
        if (!result.isSuccess())
            new FailTestCaseReport().catchExceptions(result, chrome_driver);
    }

    public  void tearDown(){
        chrome_driver.quit();
    }
}
