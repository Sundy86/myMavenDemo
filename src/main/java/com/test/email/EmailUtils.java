package com.test.email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.MalformedURLException;
import java.net.URL;

public class EmailUtils {

    public static  HtmlEmail emailSet(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName(EmailParams.host);
        email.setAuthentication(fromEmail,ps);
        email.setFrom(fromEmail);//发件人
        for(String touser:toEmail){
            email.addTo(touser);//收件人
        }

        email.setSubject(title); //邮件标题
        email.setHtmlMsg(emailMsg);//邮件内容
        return email;
    }
    public static  HtmlEmail emailSet(String title,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName(EmailParams.host);
        email.setAuthentication(fromEmail,ps);
        email.setFrom(fromEmail);//发件人
        for(String touser:toEmail){
            email.addTo(touser);//收件人
        }

        email.setSubject(title); //邮件标题

        return email;
    }

    //发送一个简单的邮件，只有内容。
    public static void  sendEmail(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        email.send();
    }

    //发送一个多个本地附件的邮件
    public static void  sendEmailWithAttachments(String title,String emailMsg,String fromEmail,String ps,String[] toEmail,String path) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        String attachmentName="apitest.xlsx,test.xml,0531_7.jpg";
        String[] attachs = attachmentName.split(",");
        for(String s:attachs){
            EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath(path);
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            emailAttachment.setDescription("测试结果");
            emailAttachment.setName(s);
            email.attach(emailAttachment);
        }
        email.send();
    }

    //发送网络上的文件的邮件。
    public static void  sendNetworkPicEmail(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException, MalformedURLException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        emailAttachment.setDescription("Apache logo 发送");
        emailAttachment.setName("Apache logo");
        email.attach(emailAttachment);
        email.send();
    }

    //发送HTML格式的邮件。
    public static void  sendHTMLEmail(String title,String fromEmail,String ps,String[] toEmail) throws EmailException, MalformedURLException {
        HtmlEmail email =emailSet(title,fromEmail,ps,toEmail);
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid =email.embed(url, "Apache logo");
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        email.send();
    }
}
