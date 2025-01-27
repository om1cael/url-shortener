package com.om1cael.url.shortener.utils;

import java.util.Random;
import java.util.random.RandomGenerator;

public class URLUtils {
    private String URL;
    private StringBuilder stringBuilder;

    public URLUtils(String URL) {
        this.URL = URL;
        this.stringBuilder = new StringBuilder(this.URL);
    }

    private void normalizeURL() {
        if(!this.URL.startsWith("http://") || !this.URL.startsWith("https://")) {
            this.stringBuilder.insert(0, "http://");
        }

        if(this.URL.endsWith("/")) {
            this.stringBuilder.deleteCharAt(this.URL.length());
        }

        this.URL = this.stringBuilder.toString();
    }

    public String createShortURL() {
        this.normalizeURL();
        this.stringBuilder.setLength(0);

        final String availableForCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final Random random = new Random();

        for(int i = 0; i < 6; i++) {
            this.stringBuilder.append(availableForCode.charAt(random.nextInt(availableForCode.length())));
        }

        return this.stringBuilder.toString();
    }
}
