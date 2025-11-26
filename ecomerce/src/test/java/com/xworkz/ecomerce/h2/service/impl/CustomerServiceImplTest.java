package com.xworkz.ecomerce.h2.service.impl;

import org.ehcache.jsr107.Eh107Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    CacheManager cacheManager;

    @Test
    public void getCacheManager() {
        Cache products = cacheManager.getCache("products");
        Object nativeCache = products.getNativeCache();
        System.out.println(products.toString());
//        System.out.println(nativeCache);
    }
}