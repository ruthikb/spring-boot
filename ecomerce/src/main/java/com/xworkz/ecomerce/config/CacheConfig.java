package com.xworkz.ecomerce.config;

import com.fasterxml.jackson.databind.cfg.CacheProvider;
import org.ehcache.core.spi.service.CacheManagerProviderService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

        MutableConfiguration<Object, Object> config = new MutableConfiguration<>()
                .setStoreByValue(false)
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));

        cacheManager.createCache("KruthikCache", config);

        return new JCacheCacheManager(cacheManager);
    }
}
