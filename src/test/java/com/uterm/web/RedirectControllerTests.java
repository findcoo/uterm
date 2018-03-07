package com.uterm.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uterm.domain.Surl;
import com.uterm.service.SurlService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * RedirectControllerTests
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class RedirectControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SurlService service;


    @Test
    public void redirect() throws Exception {
        given(this.service.get(1L)).willReturn(new Surl("http://google.com", "google.com", "")); 

        this.mvc.perform(get("/a"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void redirectToUnkownPath() throws Exception {
        given(this.service.get(1L)).willReturn(new Surl("http://dhfldfladf.com", "", "")); 

        this.mvc.perform(get("/a"))
            .andExpect(status().is3xxRedirection());
    }
    
}
