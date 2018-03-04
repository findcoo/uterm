package com.uterm.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;
import com.uterm.toolbox.Base62;

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

    private static final MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

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
    public HashMap<String, String> create(@RequestBody Surl input) throws MalformedURLException {
        URL url = new URL(input.getUrl());
        String urlText = url.toString();

        input.setHashedUrl(getHashedUrl(urlText));
        input.setDomain(url.getHost().replaceFirst("www.", ""));

        Surl addedSurl = this.surlService.add(input);
        HashMap<String, String> respMap = new HashMap<String, String>();
        respMap.put("surl", String.format("http://%s/%s", utermDomain, Base62.encode(addedSurl.getId())));
        return respMap;
    }

    @RequestMapping(value = "/{surlId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long surlId) {
        this.surlService.delete(surlId);
    }

    public static String getHashedUrl(String url) {
        md.update(url.getBytes()); 
        return Base64.getEncoder().encodeToString(md.digest());
    }
}