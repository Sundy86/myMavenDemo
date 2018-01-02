package com.test.email.mail;

import com.test.email.EmailParams;
import org.apache.commons.mail.EmailAttachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suncy on 2017/12/30 0030.
 */
public class MailSendTest {
    public static void main(String[] args) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setSubject("��������");
        mailInfo.setFromAddress(EmailParams.username);
        mailInfo.setContent("���ݣ�<h1>test,����</h1>");

        //�ռ���
        List<String> toList = new ArrayList<String>();
        String[] tos = EmailParams.tousers;
        for(String to:tos){
            toList.add(to);
        }
        mailInfo.setToAdress(toList);

        //������
        List<String> ccList = new  ArrayList<String>();
        String[] ccs = EmailParams.ccaddress;
        for(String cc: ccs){
            ccList.add(cc);
        }
        mailInfo.setCcAdress(ccList);

        //��Ӹ���
        List<EmailAttachment> attachmentList = new ArrayList<EmailAttachment>();
        String filePath ="./testdata/";
        String attachmentName="apitest.xlsx,test.xml";
        String[] attachs = attachmentName.split(",");
        for(int i=0;i<attachs.length;i++){
            EmailAttachment att = new EmailAttachment();
            att.setPath(filePath+attachs[i]);
            att.setName(attachs[i]);
            attachmentList.add(att);
        }
        mailInfo.setAttachments(attachmentList);
        MailUtil.sendMail(mailInfo);
    }
}
