package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionQty{
    @JsonProperty("WMSVPCode")
    public String wMSVPCode;
    @JsonProperty("WMSVPMethod") 
    public String wMSVPMethod;
}
