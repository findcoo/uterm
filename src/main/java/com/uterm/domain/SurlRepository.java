package com.uterm.domain;

import java.util.List;

import com.uterm.domain.Surl;

import org.springframework.data.repository.CrudRepository;


public interface SurlRepository extends CrudRepository<Surl, Long> {

    List<Surl> findByDomain(String domain);
    Surl findByHashedUrl(String hashedUrl);
}
    