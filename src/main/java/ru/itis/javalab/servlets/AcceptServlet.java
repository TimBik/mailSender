package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.Mapping;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;
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

@WebServlet("/confirm/*")
public class AcceptServlet extends HttpServlet {

    private RegistrationService registrationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        registrationService.chageStateAccept(req.getPathInfo());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        registrationService = springContext.getBean(RegistrationService.class);
    }
}
