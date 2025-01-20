package com.alura.callenge.foro.domain.topic.dto;


import com.alura.callenge.foro.domain.topic.Topic;

public record DataListTopic(Long id,
                            String title,
                            String message,
                            String timestamp,
                            boolean status,
                            String course,
                            String author,
                            String last_update) {

    public DataListTopic(Topic topic){
        this(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getTimestamp(),
                topic.isStatus(),
                topic.getCourse(),
                topic.getAuthor(),
                topic.getLast_update());
    }
}
