package com.uterm.service;

import java.util.List;

import com.uterm.domain.Surl;

/**
 * SurlService
 */
public interface SurlService {

    public Surl get(long id);

    public Surl add(Surl surl);
    
    public void delete(long id);

    public List<Surl> listByDomain(String domain);

    public Surl getHashedUrl(String hashedUrl);
}