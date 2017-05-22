package com.misyn.smartintranet.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service("emailservice")
public class EmailService {
	
	@Autowired
    private MailSender mailSender;
     
   
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    public void sendPasswordResetMail(String to,  String link) 
    {
    	final String subject="Reset your MISYN helpdesk password";
    	
    	String body="Please use following link to reset your password \n"+link;
    	
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
