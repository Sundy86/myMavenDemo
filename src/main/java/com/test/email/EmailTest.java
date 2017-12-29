package com.test.email;


import org.apache.commons.mail.EmailException;

import java.io.File;
import java.net.MalformedURLException;

public class EmailTest {

    public static void main(String[] args) throws EmailException, MalformedURLException {
        String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "apitest.xlsx";
      //  EmailUtils.sendEmail(EmailParams.title, "这是一个测试邮件", EmailParams.username, EmailParams.password, EmailParams.tousers);
     //   EmailUtils.sendEmailWithAttachments(EmailParams.title, "这是一个测试邮件", EmailParams.username, EmailParams.password, EmailParams.tousers, filePath);
       // EmailUtils.sendNetworkPicEmail(EmailParams.title, "这是一个测试邮件", EmailParams.username, EmailParams.password, EmailParams.tousers);
        EmailUtils.sendHTMLEmail(EmailParams.title,EmailParams.username, EmailParams.password, EmailParams.tousers);
    }


}
