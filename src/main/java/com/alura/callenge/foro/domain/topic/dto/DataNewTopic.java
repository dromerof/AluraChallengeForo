package com.alura.callenge.foro.domain.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record DataNewTopic(

        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotBlank
        String course,

        @NotBlank
        String author) {
}
