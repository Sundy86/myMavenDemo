package com.test.email.mail;

import com.test.email.EmailParams;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * Created by Suncy on 2017/12/30 0030.
 */
public class MailUtil {
    public static void sendMail(MailInfo mailInfo){
        HtmlEmail email = new HtmlEmail();
        try {
        // ������Ϣ
        email.setHostName(EmailParams.host);
        email.setFrom(EmailParams.username);
        email.setAuthentication(EmailParams.username,EmailParams.password);
        email.setCharset("UTF-8");
        email.setSubject(mailInfo.getSubject());
        email.setHtmlMsg(mailInfo.getContent());

        //��Ӹ���
        List<EmailAttachment> attachmentList = mailInfo.getAttachments();
        if(null != attachmentList && attachmentList.size()>0){
            for(EmailAttachment attachment:attachmentList){
                email.attach(attachment);
            }
        }
        //����ռ���
        List<String> toAddressList = mailInfo.getToAdress();
        if(null != toAddressList && toAddressList.size()>0){
            for(String toAddress:toAddressList){
                email.addTo(toAddress);
            }
        }
        //��ӳ�����
        List<String> ccAddressList = mailInfo.getCcAdress();
        if(null != ccAddressList && ccAddressList.size()>0){
            for(String ccAddress:ccAddressList){
                email.addCc(ccAddress);
            }
        }
        email.send();
        System.out.println("�ʼ����ͳɹ���");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
