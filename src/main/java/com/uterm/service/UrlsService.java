package com.uterm.service;

import java.util.List;

import com.uterm.domain.Urls;

/**
 * UrlsService
 */
public interface UrlsService {
    public Urls get(long id);
    public Urls add(Urls urls);
    public List<Urls> listByDomain(String domain);
    public Urls getHashedUrl(String hashedUrl);
}