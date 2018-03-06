package com.uterm.web;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.uterm.domain.Surl;
import com.uterm.errors.BadReqeustException;
import com.uterm.service.SurlService;
import com.uterm.toolbox.Base62;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private SurlService surlService;

    @RequestMapping(value = "/{surlCode}", method = RequestMethod.GET)
    public ResponseEntity<Void> redirect(@PathVariable String surlCode) throws MalformedURLException, URISyntaxException {
        if (surlCode.length() > 8) throw new BadReqeustException();

        Long id = Base62.decode(surlCode);
        Surl surl = this.surlService.get(id + 1);
        if (surl == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        URL url = new URL(surl.getUrl());

        HttpHeaders respHeader = new HttpHeaders();
        respHeader.setLocation(url.toURI());
        respHeader.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate");
        ResponseEntity<Void> resp = new ResponseEntity<>(respHeader, HttpStatus.MOVED_PERMANENTLY);
        return resp;
    }

    @RequestMapping("/malform")
    public void malform() throws MalformedURLException {
        throw new MalformedURLException();
    }
}