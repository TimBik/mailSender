package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.ParametrLoader;
import ru.itis.javalab.services.RegistrationService;
import ru.itis.javalab.services.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private ViewResolver viewResolver;
    private ParametrLoader parameterLoader;
    private RegistrationService registrationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        viewResolver.process("registration.ftl", new HashMap<>(), resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
        SignUpDto signUpData = parameterLoader.getRegistrationParams(request);
        registrationService.loadUserFromParameters(signUpData);
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        viewResolver = springContext.getBean(ViewResolver.class);
        parameterLoader = springContext.getBean(ParametrLoader.class);
        registrationService = springContext.getBean(RegistrationService.class);
    }

}
