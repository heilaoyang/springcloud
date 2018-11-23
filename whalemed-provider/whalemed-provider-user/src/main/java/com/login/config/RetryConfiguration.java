package com.login.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

public class RetryConfiguration {
    private static Logger log = LoggerFactory.getLogger(RetryConfiguration.class);
    @Bean
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    public RetryOperationsInterceptor configServerRetryInterceptor() {
        log.info(String.format(
                "configServerRetryInterceptor: Changing backOffOptions " +
                        "to initial: %s, multiplier: %s, maxInterval: %s",
                1000, 1.2, 5000));
        return RetryInterceptorBuilder
                .stateless()
                .backOffOptions(1000, 1.2, 5000)
                .maxAttempts(10)
                .build();
    }
}
