package com.uterm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    public void saveReturnWithId() {
        Surl surl = this.repository.save(new Surl("a", "b", "c"));

        assertThat(surl.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void findById() {
        this.entityManager.persistAndFlush(new Surl("https://test.com", "test.com", "test"));
        Surl surl = this.repository.findOne(1L);

        assertThat(surl.getUrl()).isEqualTo("https://test.com");
    }

    @Test
    @Transactional
    public void findByHashedUrl() {
        this.entityManager.persistAndFlush(new Surl("https://test.com", "test.com", "hello"));
        Surl surl = this.repository.findByHashedUrl("hello");

        assertThat(surl.getUrl()).isEqualTo("https://test.com");
    }
    
}