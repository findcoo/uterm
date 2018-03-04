package com.uterm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.uterm.domain.Surl;
import com.uterm.domain.SurlRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * SurlServiceTests
 */
@RunWith(SpringRunner.class)
public class SurlServiceTests {

    @TestConfiguration
    static class SurlServiceImplTestContextConfiguration {

        @Bean
        public SurlService surlService() {
            return new SurlServiceImpl();
        }
    }

    @Autowired
    private SurlService service;

    @MockBean
    private SurlRepository repository;

    @Test
    public void add() {
        Surl surl = new Surl("https://test.com", "test.com", "hello");
        given(this.repository.findByHashedUrl("hello")).willReturn(surl);

        assertThat(this.service.add(surl).getDomain()).isEqualTo("test.com");
    }

    @Test
    public void get() {
        given(this.repository.findOne(1L))
            .willReturn(new Surl("a", "b", "c"));

        assertThat(this.service.get(1L).getUrl())
            .isEqualTo("a");
    }
}