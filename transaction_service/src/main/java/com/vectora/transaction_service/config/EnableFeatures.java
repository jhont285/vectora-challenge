package com.vectora.transaction_service.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@EnableFeignClients(basePackages = "com.vectora.transaction_service.adapter.out.rest.account.client")
public class EnableFeatures {
}
