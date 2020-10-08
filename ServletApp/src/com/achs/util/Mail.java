package com.achs.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/5/20
 * Time: 3:35 PM
 */
public class Mail {



        private String from;
        private String password;
        private Session session;
        private MimeMessage message;
        ArrayList<String> to = new ArrayList();

        public Mail(final String var1, final String var2) {
            this.from = var1;
            this.password = var2;
            Properties var3 = new Properties();
            var3.setProperty("mail.smtp.host", "smtp.gmail.com");
            var3.setProperty("mail.smtp.port", "465");
            var3.put("mail.smtp.socketFactory.port", "465");
            var3.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            var3.setProperty("mail.smtp.auth", "true");
            this.session = Session.getDefaultInstance(var3, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(var1, var2);
                }
            });
            this.message = new MimeMessage(this.session);

            try {
                this.message.setFrom(new InternetAddress(var1));
            } catch (MessagingException var5) {
                Logger.getLogger(com.achs.util.Mail.class.getName()).log(Level.SEVERE, (String)null, var5);
            }

        }

        public Mail(String var1, int var2, final String var3, final String var4) {
            this.from = var3;
            this.password = var4;
            Properties var5 = new Properties();
            var5.setProperty("mail.smtp.host", var1);
            var5.setProperty("mail.smtp.port", String.valueOf(var2));
            var5.put("mail.smtp.socketFactory.port", "465");
            var5.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            var5.setProperty("mail.smtp.auth", "true");
            this.session = Session.getDefaultInstance(var5, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(var3, var4);
                }
            });
            this.message = new MimeMessage(this.session);

            try {
                this.message.setFrom(new InternetAddress(var3));
            } catch (MessagingException var7) {
                Logger.getLogger(com.achs.util.Mail.class.getName()).log(Level.SEVERE, (String)null, var7);
            }

        }

        public void setFrom(String var1, String var2) throws MessagingException, UnsupportedEncodingException {
            this.message.setFrom(new InternetAddress(this.from, var2));
            this.message.setSentDate(new Date());
        }

        public void addRecipient(String var1) {
            this.to.add(var1);
        }

        public void setSubject(String var1) throws MessagingException {
            this.message.setSubject(var1);
        }

        public void setText(String var1) throws MessagingException {
            this.message.setText(var1);
        }

        public void setContent(Object var1, String var2) throws MessagingException {
            this.message.setContent(var1, var2);
        }

        public void addAttatchment(Multipart var1) throws MessagingException {
            this.message.setContent(var1);
        }

        public void send() throws MessagingException {
            Iterator var1 = this.to.iterator();

            while(var1.hasNext()) {
                String var2 = (String)var1.next();
                this.message.addRecipient(Message.RecipientType.TO, new InternetAddress(var2));
            }

            Transport.send(this.message, this.message.getAllRecipients());
            System.out.println("Send Successfully.........");
        }














}
