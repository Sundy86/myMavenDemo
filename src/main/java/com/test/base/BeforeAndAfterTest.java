package com.test.base;

import com.test.SeleniumUtil;
import com.test.claim.PushClaim;
import com.test.data2.DataProviderForExcel;
import org.testng.annotations.Test;
import java.io.IOException;

public class BeforeAndAfterTest extends BeforeAndAfter {
    LoginPageElement loginPage;
    LoginCDElement loginCD;
    SeleniumUtil seleniumUtil ;

    @Test(dataProvider = "getDataFromExcel" , dataProviderClass = DataProviderForExcel.class)
    public void estimateTest(String username, String password, String captcha) throws IOException, InterruptedException {
        loginPage = new LoginPageElement(chrome_driver, log);
        seleniumUtil = new SeleniumUtil(chrome_driver,log);

        loginPage.loginToHomePage(username, password, captcha);
        log.info(chrome_driver.getCurrentUrl());
        PushClaim pc = new PushClaim();
        String accidentNo = pc.getAccidentNo();
        loginPage.setAccidentNo(accidentNo);
        loginPage.clickSearchBtn();
        seleniumUtil.sleep();
        loginPage.clickAccidentNoLink();
        seleniumUtil.longSleep();
        seleniumUtil.SwitchToNewWindow();
        loginPage.clickClaimVehicleInfoTab();

    }
     @Test(dataProvider = "getDataFromExcel" , dataProviderClass = DataProviderForExcel.class)
    public void chandaoTest(String username,String password) throws IOException, InterruptedException {
        loginCD = new LoginCDElement(chrome_driver, log);
        loginCD.loginToHomePage(username, password);
        log.info(chrome_driver.getCurrentUrl());

    }


}
