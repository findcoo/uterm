package com.uterm.service;

import java.util.List;

import com.uterm.domain.Urls;
import com.uterm.domain.UrlsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UrlsService
 */
@Service("urlsService")
public class UrlsServiceImpl implements UrlsService {

    @Autowired
    private UrlsRepository urlsRepository;

    public Urls get(long id) {
        return urlsRepository.findOne(id);
    }

    public Urls add(Urls urls) {
        return urlsRepository.save(urls);
    }

    public List<Urls> listByDomain(String domain) {
        return urlsRepository.findByDomain(domain);
    }

    public Urls getHashedUrl(String hashedUrl) {
        return urlsRepository.findByHashedUrl(hashedUrl);
    }
}