package com.clarity.ai.config;

import com.clarity.ai.model.HostsConnection;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class FobosConfiguration {


    @Bean
    public LoadingCache<String, List<HostsConnection>> cacheByHostFrom(){
        LoadingCache<String, List<HostsConnection>> logCache = CacheBuilder.newBuilder()//
                .maximumSize(3000)//
                .expireAfterWrite(65, TimeUnit.MINUTES)//
                .build(new CacheLoader<String, List<HostsConnection>>() {

                    @Override
                    public List<HostsConnection> load(String key) throws Exception {
                        return Collections.emptyList();
                        //                    return getDelegate().findByCode(codeRef).map(r -> buildReferentielFromPjRef(r));
                    }


                });
        return logCache;
    }

    @Bean
    public LoadingCache<String, List<HostsConnection>> cacheByHostTo(){
        LoadingCache<String, List<HostsConnection>> logCache = CacheBuilder.newBuilder()//
                .maximumSize(3000)//
                .expireAfterWrite(65, TimeUnit.MINUTES)//
                .build(new CacheLoader<String, List<HostsConnection>>() {

                    @Override
                    public List<HostsConnection> load(String key) throws Exception {
                        return Collections.emptyList();
                        //                    return getDelegate().findByCode(codeRef).map(r -> buildReferentielFromPjRef(r));
                    }


                });
        return logCache;
    }
}
