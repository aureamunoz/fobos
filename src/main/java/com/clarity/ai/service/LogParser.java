package com.clarity.ai.service;

import com.clarity.ai.model.HostsConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TimeZone;

@Component
public class LogParser {

    private static final java.lang.String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTF_DATETIME = DateTimeFormatter.ofPattern(DATETIME_PATTERN);


    @Autowired
    private CacheService cacheService;

    public Optional<HostsConnection> parseLine(String line) {
        if (line != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            HostsConnection hostsConnection = new HostsConnection();

            hostsConnection.setConnectionDateTime(parseTimeStamp(st.nextToken()));
            hostsConnection.setHostFrom(st.nextToken());
            hostsConnection.setHostTo(st.nextToken());

            cacheService.addToCache(hostsConnection);

            return Optional.of(hostsConnection);
        }
        return Optional.empty();

    }

    private LocalDateTime parseTimeStamp(String timestamp) {

        long instant = Long.valueOf(timestamp);
        LocalDateTime connectionDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(instant), TimeZone.getDefault().toZoneId());

        return connectionDateTime;
    }
}
