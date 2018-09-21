package com.clarity.ai.service;

import com.clarity.ai.model.HostsConnection;
import com.clarity.ai.service.CacheService;
import com.google.common.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheServiceImpl implements CacheService{

    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostFrom;

    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostTo;

    @Override
    public void addToCache(HostsConnection hostsConnection) {
        @Nullable List<HostsConnection> connectionsFrom = cacheByHostFrom.getIfPresent(hostsConnection.getHostFrom());
        if(connectionsFrom==null) {
            connectionsFrom = new ArrayList<>();
        }
        connectionsFrom.add(hostsConnection);
        cacheByHostFrom.put(hostsConnection.getHostFrom(),connectionsFrom);

        @Nullable List<HostsConnection> connectionsTo = cacheByHostTo.getIfPresent(hostsConnection.getHostTo());
        if(connectionsTo==null) {
            connectionsTo = new ArrayList<>();
        }
        connectionsTo.add(hostsConnection);
        cacheByHostTo.put(hostsConnection.getHostTo(),connectionsTo);
    }
}
