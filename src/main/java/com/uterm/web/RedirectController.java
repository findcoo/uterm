package com.uterm.web;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.lang.model.element.UnknownElementException;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;
import com.uterm.toolbox.Base62;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * RedirectController
 * 
 */
@RestController
public class RedirectController {

    private static final Logger log = LoggerFactory.getLogger(RedirectController.class);

    @Autowired
    private SurlService surlService;

    @ExceptionHandler({ MalformedURLException.class, URISyntaxException.class })
    public ResponseEntity<String> internalServerError(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("stored a malformed url, waiting until system admin fix it", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{surlCode}", method = RequestMethod.GET)
    public ResponseEntity<Void> redirect(@PathVariable String surlCode) throws MalformedURLException, URISyntaxException {
        Long id = Base62.decode(surlCode);
        Surl surl = this.surlService.get(id);
        if (surl == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        URL url = new URL(surl.getUrl());

        HttpHeaders respHeader = new HttpHeaders();
        respHeader.setLocation(url.toURI());
        ResponseEntity<Void> resp = new ResponseEntity<>(respHeader, HttpStatus.MOVED_PERMANENTLY);
        return resp;
    }

    @RequestMapping("/malform")
    public void malform() throws MalformedURLException {
        throw new MalformedURLException();
    }
}