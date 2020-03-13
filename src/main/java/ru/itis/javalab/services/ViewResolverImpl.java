package ru.itis.javalab.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ViewResolverImpl implements ViewResolver {
    @Autowired
    @Qualifier(value = "getConfig")
    private Configuration configuration;

    @Override
    public void process(String name, Map<String, String> root, Writer writer) {
        try {
            Template t = configuration.getTemplate(name);
            t.process(root, writer);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String process(String name, Map<String, String> root) {
        try {
            Template t = configuration.getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
