package ru.itis.javalab.listners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.itis.javalab.config.ApplicationContextConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebInitParam;  ?? что это
import javax.servlet.annotation.WebListener;
@Component
@WebListener
public class SpringContextServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("springContext", springContext);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
