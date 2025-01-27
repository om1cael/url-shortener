package com.om1cael.url.shortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record URLEntryDTO
    (
        @NotNull String URL,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) String shortCode
    )
{}
