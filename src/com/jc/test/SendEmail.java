package com.jc.test;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static void main(String [] args) throws MessagingException
    {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名 使用163邮箱发送
        props.setProperty("mail.host", "smtp.gmail.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("邮件主题");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 设置邮件内容
        msg.setText("邮件内容，找回密码的连接");
        // 设置发件人
        msg.setFrom(new InternetAddress("rogerzjc@gmail.com"));

        Transport transport = session.getTransport();
        // 连接邮件服务器     xzbbrvnlsjpdbfei
        transport.connect("rogerzjc@gmail.com", "不是登录密码，需要开启客户授权密码，生成授权码，此处填写授权码");
        // 发送邮件
        transport.sendMessage(msg, new Address[] {new InternetAddress("目标地址，即接收邮件的邮箱地址")});
        // 关闭连接
        transport.close();
    }
}