package com.om1cael.url.shortener.controller;

import com.om1cael.url.shortener.dto.URLEntryDTO;
import com.om1cael.url.shortener.exception.AlreadyShortenedException;
import com.om1cael.url.shortener.service.URLEntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class URLEntryController {

    @Autowired
    private URLEntryService urlEntryService;

    @PostMapping
    private ResponseEntity<URLEntryDTO> createShortURL(@RequestBody @Valid URLEntryDTO urlEntryDTO) throws AlreadyShortenedException {
        URLEntryDTO responseURLEntryDTO = this.urlEntryService.createShortURL(urlEntryDTO);
        this.urlEntryService.save(responseURLEntryDTO);
        return ResponseEntity.ok(responseURLEntryDTO);
    }

}
