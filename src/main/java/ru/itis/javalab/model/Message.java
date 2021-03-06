package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Message {
    private String from;
    private String to;
    private String subject;
    private String content;

}
