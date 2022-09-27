package com.ema.app.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo (@JsonProperty("info") String info){
        this.info = info;
    }
}
