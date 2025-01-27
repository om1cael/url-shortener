package com.om1cael.url.shortener.repository;

import com.om1cael.url.shortener.model.URLEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLEntryRepository extends JpaRepository<URLEntry, Long> {
    Optional<URLEntry> findByURL(String URL);
    Optional<URLEntry> findByShortURL(String shortURL);
}
