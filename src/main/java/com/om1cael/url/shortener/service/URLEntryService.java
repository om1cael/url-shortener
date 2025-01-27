package com.om1cael.url.shortener.service;

import com.om1cael.url.shortener.dto.URLEntryDTO;
import com.om1cael.url.shortener.exception.AlreadyShortenedException;
import com.om1cael.url.shortener.model.URLEntry;
import com.om1cael.url.shortener.repository.URLEntryRepository;
import com.om1cael.url.shortener.utils.URLUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLEntryService {

    @Autowired
    private URLEntryRepository urlEntryRepository;

    public URLEntryDTO createShortURL(URLEntryDTO urlEntryDTO) throws AlreadyShortenedException {
        if(urlAlreadyShortened(urlEntryDTO)) {
            throw new AlreadyShortenedException("URL already shortened!");
        }

        URLUtils urlUtils = new URLUtils(urlEntryDTO.URL());
        String shortURL = urlUtils.createShortURL();

        return new URLEntryDTO(urlEntryDTO.URL(), shortURL);
    }

    public URLEntry getByShortCode(String shortCode) {
        return this.urlEntryRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new EntityNotFoundException("URL not found"));
    }

    public void save(URLEntryDTO urlEntryDTO) {
        URLEntry urlEntry = new URLEntry();

        urlEntry.setURL(urlEntryDTO.URL());
        urlEntry.setShortCode(urlEntryDTO.shortCode());
        urlEntry.setClicks(0);

        this.urlEntryRepository.save(urlEntry);
    }

    private boolean urlAlreadyShortened(URLEntryDTO urlEntryDTO) {
        return this.urlEntryRepository.findByURL(urlEntryDTO.URL()).isPresent();
    }
}
