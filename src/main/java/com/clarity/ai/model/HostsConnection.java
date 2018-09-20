package com.clarity.ai.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class HostsConnection {

    private LocalDateTime connectionDateTime;

    private String hostFrom;

    private String hostTo;

    public LocalDateTime getConnectionDateTime() {
        return connectionDateTime;
    }

    public void setConnectionDateTime(LocalDateTime connectionDateTime) {
        this.connectionDateTime = connectionDateTime;
    }

    public String getHostFrom() {
        return hostFrom;
    }

    public void setHostFrom(String hostFrom) {
        this.hostFrom = hostFrom;
    }

    public String getHostTo() {
        return hostTo;
    }

    public void setHostTo(String hostTo) {
        this.hostTo = hostTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        HostsConnection other = (HostsConnection) o;

        return Objects.equals(connectionDateTime,other.connectionDateTime)
                && Objects.equals(hostFrom,other.hostFrom)
                && Objects.equals(hostTo,other.hostTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionDateTime,hostFrom,hostTo);
    }

}
