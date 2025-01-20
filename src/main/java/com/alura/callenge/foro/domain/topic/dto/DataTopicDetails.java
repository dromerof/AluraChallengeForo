package com.alura.callenge.foro.domain.topic.dto;


public record DataTopicDetails(String title,
                               String message,
                               String timestamp,
                               boolean status,
                               String author,
                               String course,
                               String updated) {
}
