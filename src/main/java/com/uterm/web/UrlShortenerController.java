package com.uterm.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * SurlhortenerController
 */
@RestController
@RequestMapping("/surl")
public class UrlShortenerController {

    @Autowired
    SurlService SurlService;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="malformed URL")
    @ExceptionHandler(MalformedURLException.class)
    public void badRequest() {}

    @RequestMapping(method = RequestMethod.POST)
    public Surl create(@RequestBody Surl input) throws MalformedURLException, NoSuchAlgorithmException {
        URL url = new URL(input.getUrl());
        String urlText = url.toString();
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(urlText.getBytes()); 
        input.setHashedUrl(Base64.getEncoder().encodeToString(md.digest()));
        input.setUrl(urlText);
        input.setDomain(url.getHost().replaceFirst("www.", ""));

        return SurlService.add(input);
    }
}