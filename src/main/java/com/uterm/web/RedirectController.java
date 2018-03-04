package com.uterm.web;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;
import com.uterm.toolbox.Base62;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String redirect(@PathVariable String surlCode) {
        Long id = Base62.decode(surlCode);
        Surl surl = this.surlService.get(id);

        return "redirect:" + surl.getUrl();
    }
    
}