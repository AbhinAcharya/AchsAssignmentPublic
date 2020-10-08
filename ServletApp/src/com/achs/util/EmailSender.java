package com.achs.util;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/5/20
 * Time: 4:04 PM
 */
//generated for testing purpose
public class EmailSender {
    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        Mail mail = new Mail("sujanthapa2790@gmail.com","computare1file");
        //from
        mail.setFrom("sujanthapa2790@gmail.com","Achs mail");
        mail.setSubject("About Registration");
        mail.setContent("<h1>Hi Abhin your registration is sucess</h1>","text/html");
        //recipent add garne hae
        mail.addRecipient("abhin.acharya2012@gmail.com");
        mail.send();
    }
}
