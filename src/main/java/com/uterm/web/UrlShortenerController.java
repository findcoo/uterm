package com.uterm.web;

import com.uterm.domain.Urls;
import com.uterm.service.UrlsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * UrlShortenerController
 */
@RestController
@RequestMapping("/urls")
public class UrlShortenerController {

    @Autowired
    UrlsService urlsService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Urls input) {
        urlsService.add(input);
    }
}