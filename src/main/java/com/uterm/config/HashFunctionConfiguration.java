package com.uterm.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HashFunctionConfiguration
 */
@Configuration
public class HashFunctionConfiguration {

    @Bean(autowire = Autowire.BY_NAME)
    public MessageDigest mdSHA256() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256");
    }
}