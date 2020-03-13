package ru.itis.javalab.services;

import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.Map;

public interface ViewResolver {
    void process(String name, Map<String, String> root, Writer writer);

    String process(String name, Map<String, String> root);
}
