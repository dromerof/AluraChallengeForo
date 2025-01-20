package com.alura.callenge.foro.domain.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(

        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotBlank
        String course,

        @NotNull
        boolean status) {
}
