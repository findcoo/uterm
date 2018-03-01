package com.uterm.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
public class Urls {
    @Id
    @GeneratedValue
    private Long id;
    private String domain;
    @Column(unique=true)
    private String hashedUrl;
    private String url;
    @CreatedDate
    private LocalDateTime CreatedAt;
    @LastModifiedDate
    private LocalDateTime ModifiedAt;

    protected Urls() {}

    public Urls(String url, String domain, String hashedUrl) {
        this.domain = domain;
        this.url = url;
        this.hashedUrl = hashedUrl;
    }
}