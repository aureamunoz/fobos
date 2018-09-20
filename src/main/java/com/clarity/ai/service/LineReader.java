package com.clarity.ai.service;

import com.clarity.ai.model.HostsConnection;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Component
public class LineReader implements CommandLineRunner {

    public static final String CONNECTIONS_HOSTS_FILENAME = "connections-logfile";

    @Autowired
    private LogParser logParser;

    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostFrom;

    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostTo;


    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new ClassPathResource(CONNECTIONS_HOSTS_FILENAME).getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                Optional<HostsConnection> hostsConnection = logParser.parseLine(line);
                hostsConnection.ifPresent(System.out::println);
            }
            Thread.sleep(10000);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
