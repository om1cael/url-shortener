package com.om1cael.url.shortener.controller;

import com.om1cael.url.shortener.dto.URLEntryDTO;
import com.om1cael.url.shortener.exception.AlreadyShortenedException;
import com.om1cael.url.shortener.model.URLEntry;
import com.om1cael.url.shortener.service.URLEntryService;
import com.om1cael.url.shortener.utils.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/")
public class URLEntryController {

    @Autowired
    private URLEntryService urlEntryService;

    @Autowired
    private RateLimiter rateLimiter;

    @PostMapping
    private ResponseEntity<URLEntryDTO> createShortURL(@RequestBody @Valid URLEntryDTO urlEntryDTO) throws AlreadyShortenedException {
        if(this.rateLimiter.tryConsume()) {
            URLEntryDTO responseURLEntryDTO = this.urlEntryService.createShortURL(urlEntryDTO);
            this.urlEntryService.save(responseURLEntryDTO);
            return ResponseEntity.ok(responseURLEntryDTO);
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/{shortCode}")
    private ResponseEntity<Void> accessURL(@PathVariable String shortCode) {
        if(this.rateLimiter.tryConsume()) {
            URLEntry urlEntry = this.urlEntryService.getByShortCode(shortCode);
            this.urlEntryService.incrementClicksAndSave(urlEntry);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlEntry.getURL()))
                    .build();
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
