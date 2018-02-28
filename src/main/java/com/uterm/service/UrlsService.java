package com.uterm.service;

import java.util.List;

import com.uterm.domain.Urls;

/**
 * UrlsService
 */
public interface UrlsService {
   public List<Urls> listByDomain(String domain);
}