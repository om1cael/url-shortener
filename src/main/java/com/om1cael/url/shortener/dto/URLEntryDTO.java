package com.om1cael.url.shortener.dto;

import jakarta.validation.constraints.NotNull;

public record URLEntryDTO(@NotNull String URL) {}
