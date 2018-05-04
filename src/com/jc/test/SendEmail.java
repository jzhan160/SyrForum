////////////////////////////////////////////////////////////////////
// sendEmail.java   test class                                    //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class sendEmail which test the function
 * of resetting password by sending a email.
 *
 * Maintenance history
 * Version 1.0
 * 05/04/2018
 *
 * */

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
        // open debug mode
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        // Set the mail server host name and use 163 mailboxes to send
        props.setProperty("mail.host", "smtp.gmail.com");
        // Send mail protocol name
        props.setProperty("mail.transport.protocol", "smtp");
        // Setting up environmental information
        Session session = Session.getInstance(props);

        // Create a mail object
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("main subject");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Set message content
        msg.setText("the link to reset the password");
        // Set sender
        msg.setFrom(new InternetAddress("rogerzjc@gmail.com"));

        Transport transport = session.getTransport();
        // Connect to mail server
        transport.connect("rogerzjc@gmail.com", "It is not a login password. You need to open the client authorization password and generate an authorization code. Fill in the authorization code here.");
        // send email
        transport.sendMessage(msg, new Address[] {new InternetAddress("Destination address, the email address of the receiving email")});
        // Close the connection
        transport.close();
    }
}