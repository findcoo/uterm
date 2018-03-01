package com.uterm.domain;

import java.util.List;

import com.uterm.domain.Urls;

import org.springframework.data.repository.CrudRepository;


public interface UrlsRepository extends CrudRepository<Urls, Long> {

    List<Urls> findByDomain(String Domain);
}
    