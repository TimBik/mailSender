package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import ru.itis.javalab.repositories.UserRepositoryImpl;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.services.*;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:properties/application.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationContextConfig {

    @Autowired
    private Environment environment;

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfigurationFactoryBean() {
        FreeMarkerConfigurationFactoryBean freeMarkerFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerFactoryBean.setTemplateLoaderPath("classpath:/templates");
        freeMarkerFactoryBean.setPreferFileSystemAccess(true);
        freeMarkerFactoryBean.setDefaultEncoding("UTF-8");
        return freeMarkerFactoryBean;
    }

    @Bean
    public freemarker.template.Configuration getConfig(FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean) {
        return freeMarkerConfigurationFactoryBean.getObject();
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));

        mailSender.setUsername(environment.getProperty("mail.user"));
        mailSender.setPassword(environment.getProperty("mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public freemarker.template.Configuration getFreeMarkerConfiguration() {
        return getFreeMarkerConfigurationFactoryBean().getObject();
    }

    @Bean
    public MailService mailService() {
        return new MailServiceImpl();
    }

    @Bean
    public ViewResolver getViewResolver() {
        return new ViewResolverImpl();
    }

    @Bean
    public ParametrLoader getParametrLoader() {
        return new ParametrLoaderImpl();
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(hikariDataSource());
    }

    @Bean
    public UsersRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public RegistrationService getRegistrationService() {
        return new RegistrationServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.user"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        return config;
    }

    @Bean
    public DataSource hikariDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public PrepareMailModel prepareMailModel() {
        return new PrepareMailModelImpl();
    }

}
