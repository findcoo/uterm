package com.uterm.config;

import org.springframework.data.domain.AuditorAware;

/**
 * AuditorAwareImpl
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    public String getCurrentAuditor() {
        return "JPA auditor";
    }
}