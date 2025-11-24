package com.xworkz.ecomerce.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public JCacheManagerCustomizer cacheCustomizer() {
        return cacheManager -> cacheManager.createCache("products",
                new MutableConfiguration<>()
                        .setExpiryPolicyFactory(
                                CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 5)))
                        .setStoreByValue(false)
        );
    }

}
