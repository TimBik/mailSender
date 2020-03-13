package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MailServiceImpl implements MailService {
    @Autowired
    private ViewResolver viewResolver;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMessage(String subject, String mail, Map model) {
        String html = viewResolver.process("mail.ftl", model);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            messageHelper.setFrom("Nobody");
            messageHelper.setTo(mail);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };
        mailSender.send(messagePreparator);
    }
}
