package com.test.email.mail;

import org.apache.commons.mail.EmailAttachment;

import java.util.List;

/**
 * Created by Suncy on 2017/12/30 0030.
 */
public class MailInfo {
    //发件人
    private String fromAddress;
    //收件人
    private List<String> toAdress;
    //抄送人
    private List<String> ccAdress;
    // 附件信息
    private List<EmailAttachment> attachments;
    //主题
    private String subject;
    //邮件内容
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
