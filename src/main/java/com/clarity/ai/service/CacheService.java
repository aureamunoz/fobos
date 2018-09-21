package com.clarity.ai.service;

import com.clarity.ai.model.HostsConnection;

/****
 *
 * Java service for using guava cache
 *
 */

public interface CacheService {

    /****
     *
     * Adds an hostsConnection object to the guava cache
     *
     * @param hostsConnection
     */
    public void addToCache(HostsConnection hostsConnection);

}
