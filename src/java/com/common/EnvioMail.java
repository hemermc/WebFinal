/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author alexandermunguiaclemente
 */
public class EnvioMail {
    public void envioSingleCorreo(String to, String nombre, String mensaje){
      String from = "proyectopatrones2019@gmail.com";//change accordingly

     //Get the session object
      Properties properties = System.getProperties();
       properties.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    properties.put("mail.smtp.user", from);
    properties.put("mail.smtp.clave", "patrones2019");    //La clave de la cuenta
    properties.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    properties.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    properties.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
      Session session = Session.getDefaultInstance(properties);

     //compose the message
      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
         message.setSubject("Notificación - Para: " + nombre);
         message.setText(mensaje);

         // Send message
         Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", from, "patrones2019");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
         System.out.println("message sent successfully....");

      }catch (MessagingException mex) {mex.printStackTrace();}
   }  
}

