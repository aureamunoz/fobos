package com.clarity.ai.service;

/**
 * A simple java interface that read data from a log file input source
 */

public interface LogReader {

    /***
     * Launchs the reading of file
     *
     * @param filename path of file
     */
    public void startReading(String filename);
}
