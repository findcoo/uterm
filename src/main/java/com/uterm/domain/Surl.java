package com.uterm.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
public class Surl {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable=false)
    private String domain;

    @NotBlank
    @Column(unique=true, nullable=false)
    private String hashedUrl;

    @NotBlank
    @Column(nullable=false)
    private String url;

    @CreatedDate
    private LocalDateTime CreatedAt;

    @LastModifiedDate
    private LocalDateTime ModifiedAt;

    protected Surl() {}

    public Surl(String url, String domain, String hashedUrl) {
        this.domain = domain;
        this.url = url;
        this.hashedUrl = hashedUrl;
    }
}