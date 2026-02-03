package com.gestionandamios.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    // Configura aquí tu correo de envío (puedes usar una cuenta de Gmail de prueba)
    private final String remitente = "valenr.v22@gmail.com"; 
    private final String clave = "bzmlwwfnjwlbatll"; // No es tu clave normal, es una 'App Password'

    public void enviarCodigo(String destinatario, String codigo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Código de Recuperación - Andamios Viviescas");
            message.setText("Su código de seguridad es: " + codigo + "\n\nNo comparta este código con nadie.");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}