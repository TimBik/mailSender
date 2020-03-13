package ru.itis.javalab.services;


import ru.itis.javalab.dto.SignUpDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ParametrLoader {
    public SignUpDto getRegistrationParams(HttpServletRequest req);
}
