package com.xworkz.ecomerce.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheckUp implements HealthIndicator {


    @Override
    public Health health() {
        if (isHealthy()){
            return Health.up().withDetail("healthCheck","all is working fine").build();
        }
        return Health.down().withDetail("healthCheck","Not working").build();
    }
    private boolean isHealthy(){
        return false;
    }

    @Autowired
    private CacheManager cacheManager;

    public void printCacheContents() {
        Cache cache = cacheManager.getCache("kruthikCache");
        if (cache != null) {
            Object nativeCache = cache.getNativeCache();
            System.out.println(nativeCache);
        }
    }

}

