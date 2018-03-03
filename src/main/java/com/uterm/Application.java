package com.uterm;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;
import com.uterm.toolbox.Base62;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@Autowired
	private SurlService surlService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/{encodedURL}")
	public String redirect(@PathVariable String encodedURL) {
		Long id = Base62.decode(encodedURL);	
		Surl surl = surlService.get(id);
		
		return "redirect" + surl.getUrl();
	}
}
