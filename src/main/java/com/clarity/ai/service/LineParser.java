package com.clarity.ai.service;

import com.clarity.ai.model.HostsConnection;

import java.util.Optional;

/***
 *
 * Java service for converting data input to java object
 *
 */

public interface LineParser {

    /***
     * Creates un object HostsConnection containing the data of line parameter
     * @param line The line readen from file input
     * @return the HostsConnection object
     */

    public Optional<HostsConnection> parseLine(String line);
}
