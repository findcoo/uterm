package com.uterm.web;

import static com.uterm.toolbox.HashFunction.digestWithSHA256;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private SurlService service;

    @Test
    public void createSurl() throws Exception {
        Surl surl = new Surl("http://test.com", "test.com", digestWithSHA256(new String[]{"http://test.com"}));
        surl.setId(1L);
        given(this.service.add(surl)).willReturn(surl);

        this.mvc.perform(
            post("/surl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(surl)))
            .andDo(print())
            .andExpect(status().isOk());
    } 

    @Test
    public void getSurl() throws Exception {
        Surl surl = new Surl("http://get.com", "get.com", "");
        given(this.service.get(1L)).willReturn(surl);

        this.mvc.perform(get("/surl/1"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void sendBlankUrl() throws Exception {
        Surl surl = new Surl(" ", "", "");
        surl.setId(1L);
        given(this.service.add(surl)).willReturn(surl);

        this.mvc.perform(
            post("/surl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(surl)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void casesShouldBeBadReqeust() throws Exception {
        this.mvc.perform(delete("/surl/wrong"))
            .andExpect(status().isBadRequest());

        this.mvc.perform(get("/surl/wrong"))
            .andExpect(status().isBadRequest());
    }

    public static String json(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}