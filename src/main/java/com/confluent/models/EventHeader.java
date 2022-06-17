package com.confluent.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EventHeader{
    @JsonProperty("MsgType")
    public String msgType;
    @JsonProperty("SendingTime") 
    public Date sendingTime;
    @JsonProperty("WMHeader") 
    public WMHeader wMHeader;
}
