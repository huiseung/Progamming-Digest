package com.example.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private static Logger logger = LoggerFactory.getLogger(MemberRepository.class);

    public Member findByNameNoCache(String name){
        slowQuery(2000);
        return new Member(0, name+"@gmail.com", name);
    }

    @Cacheable(value = "findMemberCache", key="#name")
    public Member findByNameCache(String name){
        slowQuery(2000);
        return new Member(0, name+"@gmail.com", name);
    }

    @CacheEvict(value = "findMemberCache", key="#name")
    public void refresh(String name){
        logger.info(name + "의 cache clear");
    }

    //처리속도가 오래걸리는 쿼리를 처리하는 상황을 만듬
    private void slowQuery(long seconds){
        try{
            Thread.sleep(seconds);
        }
        catch (InterruptedException e){
            throw new IllegalStateException(e);
        }
    }
}
