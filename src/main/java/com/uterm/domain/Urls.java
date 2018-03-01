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
    private String urlB62;
    @Column(unique=true)
    private String hashedUrl;
    @CreatedDate
    private LocalDateTime CreatedAt;
    @LastModifiedDate
    private LocalDateTime ModifiedAt;

    private Urls(String domain, String urlB62, String hashedUrl) {
        this.domain = domain;
        this.urlB62 = urlB62;
        this.hashedUrl = hashedUrl;
    }
}