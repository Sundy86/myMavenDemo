package com.test.base;

import com.test.SeleniumUtil;
import com.test.claim.PushClaim;
import com.test.data2.DataProviderForExcel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.test.PropertiesUtil.CHROME_DRIVER_PATH;

public class ClearCache extends BeforeAndAfter1 {
    LoginPageElement loginPage;
    LoginCDElement loginCD;
    SeleniumUtil seleniumUtil ;


    public void clearCacheTest() throws IOException, InterruptedException {
        loginPage = new LoginPageElement(chrome_driver, log);
        seleniumUtil = new SeleniumUtil(chrome_driver,log);
        loginPage.loginToCachePage("scc", "1", "1");
        log.info(chrome_driver.getCurrentUrl());
        loginPage.clearCache();

    }
    public static void main(String[] args) throws IOException, InterruptedException {
       new ClearCache().clearCacheTest();
    }
}


