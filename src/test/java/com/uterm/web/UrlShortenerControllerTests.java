package com.uterm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uterm.domain.Surl;
import com.uterm.service.SurlService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * UrlShortenerController
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class UrlShortenerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SurlService surlService;

    @Test
    public void createSurl() throws Exception {
        this.mvc.perform(
            post("/surl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(new Surl("http://test.com", "", ""))))
            .andDo(print())
            .andExpect(status().isOk());
    } 

    public void sendBlankUrl() throws Exception {
        this.mvc.perform(
            post("/surl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(new Surl(" ", "", ""))))
            .andExpect(status().isBadRequest());
    }

    public void deleteWithWrongTypeParam() throws Exception {
        this.mvc.perform(delete("/url/wrong"))
            .andExpect(status().isBadRequest());
    }

    public static String json(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}