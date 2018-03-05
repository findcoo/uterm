package com.uterm.web;

import static com.uterm.toolbox.HashFunction.digestWithSHA256;

import java.net.MalformedURLException;
import java.net.URL;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Value("${uterm.domain.name}")
    private String utermDomain;

    @Autowired
    private SurlService surlService;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="check the request input")
    @ExceptionHandler({ MalformedURLException.class })
    public void badRequest() {}

    @RequestMapping(value = "/{surlId}", method = RequestMethod.GET)
    public ResponseEntity<Surl> get(@PathVariable Long surlId) {
        Surl surl = this.surlService.get(surlId);
        ResponseEntity<Surl> resp = new ResponseEntity<Surl>(surl, 
            (surl != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        return resp;
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public Surl create(@RequestBody Surl input) throws MalformedURLException {
        URL url = new URL(input.getUrl());
        String urlText = url.toString();

        input.setHashedUrl(digestWithSHA256(new String[]{urlText}));
        input.setDomain(url.getHost().replaceFirst("www.", ""));

        return this.surlService.add(input);
    }

    @RequestMapping(value = "/{surlId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long surlId) {
        this.surlService.delete(surlId);
    }
}