package com.uterm.web;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * SurlhortenerController
 */
@RestController
@RequestMapping("/Surl")
public class UrlShortenerController {

    @Autowired
    SurlService SurlService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Surl input) {
        SurlService.add(input);
    }
}