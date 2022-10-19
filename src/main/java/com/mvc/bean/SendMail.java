package com.mvc.bean;

import com.mvc.dao.VolDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendMail {
    public  static Object a;
    public static void go(String toEmail) throws MessagingException, IOException {
        //authentication info

//        final String username = "fatmita1998@gmail.com";
//        final String password = "tdsrkliekxavfjdg";
        //hado laa
//        final String username = "fatho.kahssi1995@gmail.com";
//        final String password = "rqrfjjkoxiifxcwd";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");

        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        //Start our mail message
        Message message = null;

        message = prepareMessage(session, username,toEmail);
        Transport.send(message);

    }

    private static Message prepareMessage(Session session, String username, String toEmail) throws MessagingException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("Confirmation Booking");
        VolDao volDao=new VolDao();
            message.setText("TypeBooking : " + volDao.myArrayList.get(0)+ "\n"+"nombre de places" + volDao.myArrayList.get(1)+"\n"+
                    "Start_city  : " + volDao.myArrayList.get(2)+ "\n" + "end_city" +volDao.myArrayList.get(3)+"\n"+volDao.myArrayList.get(4) +"\n"+ volDao.myArrayList.get(5));

        return  message;
    }
}
