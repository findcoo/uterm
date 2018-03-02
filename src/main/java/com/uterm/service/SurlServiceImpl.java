package com.uterm.service;

import java.util.List;

import com.uterm.domain.Surl;
import com.uterm.domain.SurlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SurlService
 */
@Service("SurlService")
@Transactional
public class SurlServiceImpl implements SurlService {

    @Autowired
    private SurlRepository SurlRepository;

    public Surl get(long id) {
        return SurlRepository.findOne(id);
    }

    public Surl add(Surl Surl) {
        return SurlRepository.save(Surl);
    }

    public List<Surl> listByDomain(String domain) {
        return SurlRepository.findByDomain(domain);
    }

    public Surl getHashedUrl(String hashedUrl) {
        return SurlRepository.findByHashedUrl(hashedUrl);
    }
}