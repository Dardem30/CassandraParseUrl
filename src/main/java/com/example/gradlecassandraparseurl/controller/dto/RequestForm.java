package com.example.gradlecassandraparseurl.controller.dto;

public class RequestForm {
    private String url;
    private boolean isPrivate;
    private String ip;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
