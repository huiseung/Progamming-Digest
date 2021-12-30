package com.example.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheManagerCheck implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(CacheManagerCheck.class);
    private final CacheManager cacheManager;
    public CacheManagerCheck(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(
                "\n\n"+
                "=".repeat(50)+"\n"
                +this.cacheManager.getClass().getName() +"\n"
                +"=".repeat(50)+"\n\n");
    }
}
