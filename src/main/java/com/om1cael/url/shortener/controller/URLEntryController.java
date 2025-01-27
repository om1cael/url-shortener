package com.om1cael.url.shortener.controller;

import com.om1cael.url.shortener.dto.URLEntryDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class URLEntryController {

    @PostMapping
    private void createShortURL(@RequestBody @Valid URLEntryDTO urlEntryDTO) {

    }

}
