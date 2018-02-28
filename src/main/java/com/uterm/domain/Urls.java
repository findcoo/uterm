package com.uterm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Urls {
    private @Id @GeneratedValue Long id;
    private String domain;
    private String urlB62;

    private Urls(String domain, String url) {
        this.domain = domain;
        this.urlB62 = url;
    }
}