package ru.itis.javalab.services;

import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.SignUpDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParametrLoaderImpl implements ParametrLoader {

    @Override
    public SignUpDto getRegistrationParams(HttpServletRequest req) {
        return SignUpDto.builder()
                .mail(req.getParameter("email"))
                .login(req.getParameter("login"))
                .password( req.getParameter("password"))
                .build();
    }

}
