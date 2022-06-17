package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WMExtRefDatum{
    @JsonProperty("WMExtRefID")
    public String wMExtRefID;
    @JsonProperty("WMExtRefIDType") 
    public String wMExtRefIDType;
    @JsonProperty("WMExtRefIDSource") 
    public String wMExtRefIDSource;
}
