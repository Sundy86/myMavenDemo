package com.test.email.mail;

import org.apache.commons.mail.EmailAttachment;

import java.util.List;

/**
 * Created by Suncy on 2017/12/30 0030.
 */
public class MailInfo {
    //������
    private String fromAddress;
    //�ռ���
    private List<String> toAdress;
    //������
    private List<String> ccAdress;
    // ������Ϣ
    private List<EmailAttachment> attachments;
    //����
    private String subject;
    //�ʼ�����
    private String content;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getToAdress() {
        return toAdress;
    }

    public void setToAdress(List<String> toAdress) {
        this.toAdress = toAdress;
    }

    public List<String> getCcAdress() {
        return ccAdress;
    }

    public void setCcAdress(List<String> ccAdress) {
        this.ccAdress = ccAdress;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EmailAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
