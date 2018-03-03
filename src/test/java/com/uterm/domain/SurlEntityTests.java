package com.uterm.domain;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SurlEntityTests
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SurlEntityTests {
   
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManger;

    @Test
    public void urlIsBlankShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl(" ", "test.com", "test");
        entityManger.persist(surl);
    }

    @Test
    public void urlIsNullShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl(null, "test.com", "test");
        entityManger.persist(surl);
    }

    @Test
    public void hashedUrlIsBlankShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl("https://test.com/hello", "test.com", " ");
        entityManger.persist(surl);
    }

    @Test
    public void hashedUrlIsNullShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl("https://test.com/hello", "test.com", null);
        entityManger.persist(surl);
    }

    @Test
    public void domainIsBlankShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl("https://test.com/hello", "    ", "test");
        entityManger.persist(surl);
    }

    @Test
    public void domainIsNullShouldThrowException() {
        thrown.expect(ConstraintViolationException.class);
        Surl surl = new Surl("https://test.com/hello", null, "test");
        entityManger.persist(surl);
    }

    @Test
    public void saveProperData() {
        Surl surl = this.entityManger.persistFlushFind(new Surl("https://test.com/hell", "test.com", "test"));
        assertEquals(surl.getDomain(), "test.com");
    }
}