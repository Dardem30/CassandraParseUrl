package com.example.gradlecassandraparseurl.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestForm {
    private String url;
    private boolean isPrivate;

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
}
