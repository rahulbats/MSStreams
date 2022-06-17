package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventTrailer{
    @JsonProperty("CheckSum")
    public String checkSum;
}
