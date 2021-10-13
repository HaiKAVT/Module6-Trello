package com.trello.service.email;

import com.trello.model.Board;
import com.trello.model.User;
import com.trello.model.Workspace;
import com.trello.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserService userService;
    public void sendVerificationEmail(User user)
            throws MessagingException {
        String subject = "Register notification";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getUsername() + ",</p>";
        mailContent += "<p> Bạn đã được một người bạn mời vào nhóm ";
        mailContent += "<a href ='http://localhost:4200/login'> Bấm vào đây để đăng nhập ! </a> </p>" ;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom(senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
}
