package com.uterm.service;

import java.util.List;

import javax.transaction.Transactional;

import com.uterm.domain.Surl;
import com.uterm.domain.SurlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SurlService
 */
@Service("surlService")
@Transactional
public class SurlServiceImpl implements SurlService {

    @Autowired
    private SurlRepository surlRepository;

    public Surl get(Long id) {
        return this.surlRepository.findOne(id);
    }

    public Surl add(Surl surl) {
        Surl existsSurl = this.surlRepository.findByHashedUrl(surl.getHashedUrl());

        return (existsSurl != null) ? existsSurl : this.surlRepository.save(surl);
    }

    public void delete(Long id) {
        this.surlRepository.delete(id);
    }

    public List<Surl> listByDomain(String domain) {
        return this.surlRepository.findByDomain(domain);
    }

    public Surl getHashedUrl(String hashedUrl) {
        return this.surlRepository.findByHashedUrl(hashedUrl);
    }
}