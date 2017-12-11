package com.test.rulecancle;

import com.test.SeleniumUtil;
import com.test.base.BeforeAndAfter;
import com.test.base.LoginCDElement;
import com.test.base.LoginPageElement;
import com.test.claim.PushClaim;
import com.test.data2.DataProviderForExcel;
import org.testng.annotations.Test;

import java.io.IOException;

public class RuleCancleCase extends BeforeAndAfter {
    LoginPageElement loginPage;
    RuleSetPageElement rulesetPage;
    SeleniumUtil seleniumUtil ;
    @Test(dataProvider = "getDataFromExcel" , dataProviderClass = DataProviderForExcel.class)
    public void cancleRule(String ruleNO) throws IOException, InterruptedException {
        loginPage = new LoginPageElement(chrome_driver, log);
        seleniumUtil = new SeleniumUtil(chrome_driver,log);
        loginPage.loginToHomePage("scc","1","1");
        log.info(chrome_driver.getCurrentUrl());

        rulesetPage = new RuleSetPageElement(chrome_driver,log);
        rulesetPage.openRuleList();
        seleniumUtil.longSleep();
        rulesetPage.openRuleSetWindow(ruleNO);
        rulesetPage.setRuleDisable();
    }
}
