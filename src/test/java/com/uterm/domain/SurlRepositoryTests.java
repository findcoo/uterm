package com.uterm.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SurlRepositoryTests
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SurlRepositoryTests {
    
   @Autowired
   private TestEntityManager entityManager;

    @Autowired
    private SurlRepository repository;
   
    @Test
    public void findById() {
        this.entityManager.persist(new Surl("https://test.com", "test.com", "test"));
        Surl surl = this.repository.findOne(1L);
        assertEquals(surl.getUrl(), "https://test.com");
    }

    @Test
    public void findByHashedUrl() {
        this.entityManager.persist(new Surl("https://test.com", "test.com", "hello"));
        Surl surl = this.repository.findByHashedUrl("hello");
        assertEquals(surl.getUrl(), "https://test.com");
    }
}