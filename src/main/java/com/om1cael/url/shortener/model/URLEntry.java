package com.om1cael.url.shortener.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "url_entries")
public class URLEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String URL;

    @NotNull
    private String shortCode;

    @NotNull
    private int clicks;
}
