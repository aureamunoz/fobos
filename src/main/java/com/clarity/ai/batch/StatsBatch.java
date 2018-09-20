package com.clarity.ai.batch;

import com.clarity.ai.model.HostsConnection;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Component
public class StatsBatch {

    @Value("${fobos.stats.to.hostname}")
    private String toHostname;

    @Value("${fobos.stats.from.hostname}")
    private String fromHostname;


    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostFrom;

    @Autowired
    private LoadingCache<String, List<HostsConnection>> cacheByHostTo;



    @Scheduled(cron = "${fobos.stats.cron}")
    public void generateStats() throws ExecutionException {
        System.out.println(" --------------- from stats --------------------\n");
        writeStats();

    }


    public void writeStats() throws ExecutionException {
        try  {
            String filename = "tmp/fobos-stats-"+ System.currentTimeMillis() + ".txt";

            String content = buildContent();

            FileOutputStream outputStream = new FileOutputStream(filename);
            byte[] strToBytes = content.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private String buildContent() throws ExecutionException {

        StringBuilder sb = new StringBuilder();
        sb.append(getHostsConnectedFrom());
        sb.append(getHostsConnectedTo());
        sb.append(getHostnameWithMostConnections());
        System.out.println(sb.toString());

        return sb.toString();
    }

    private String getHostsConnectedFrom() throws ExecutionException {
        List<HostsConnection> hostsConnections = cacheByHostFrom.get(fromHostname);
        List<String> hostnames = Collections.emptyList();
        if(hostsConnections!=null){
            hostnames = hostsConnections.stream().map(HostsConnection::getHostTo).collect(toList());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n[hostnames connected from ");
        sb.append(fromHostname);
        sb.append("]");
        sb.append("\n");
        sb.append(String.join(",", hostnames));
        sb.append("\n");
        System.out.println(sb.toString());

        return sb.toString();
    }

    private String getHostsConnectedTo() throws ExecutionException {
        List<HostsConnection> hostsConnections = cacheByHostTo.get(toHostname);
        List<String> hostnames = Collections.emptyList();
        if(hostsConnections!=null){
            hostnames = hostsConnections.stream().map(HostsConnection::getHostFrom).collect(toList());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n[hostnames connected to ");
        sb.append(toHostname);
        sb.append("]");
        sb.append("\n");
        sb.append(String.join(",", hostnames));
        sb.append("\n");
        System.out.println(sb.toString());

        return sb.toString();
    }

    private String getHostnameWithMostConnections(){

        Optional<List<HostsConnection>> connectionsFrom = cacheByHostFrom.asMap().values().stream().max(Comparator.comparingInt(List::size));
        Optional<List<HostsConnection>> connectionsTo = cacheByHostTo.asMap().values().stream().max(Comparator.comparingInt(List::size));
        String mostConnectedHostname = "";


        if(connectionsFrom.get().size()>connectionsTo.get().size()){
            mostConnectedHostname = connectionsFrom.get().get(0).getHostFrom();
        }else if (connectionsFrom.get().size()<connectionsTo.get().size()){
            mostConnectedHostname = connectionsTo.get().get(0).getHostTo();
        }else{
            StringBuilder sbc = new StringBuilder();
            sbc.append(connectionsFrom.get().get(0).getHostFrom());
            sbc.append(",");
            sbc.append(connectionsTo.get().get(0).getHostTo());
            mostConnectedHostname = sbc.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n[the hostname that generated most connections in the last hour]\n");
        sb.append(mostConnectedHostname);
        sb.append("\n");
        System.out.println(sb.toString());

        return sb.toString();



    }




}
