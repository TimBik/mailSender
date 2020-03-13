package ru.itis.javalab.services;

import org.springframework.stereotype.Component;
import ru.itis.javalab.model.User;

import java.util.Map;

public interface MailService {
    void sendMessage(String subject, String mail, Map model);
}
