package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WMHeader{
    @JsonProperty("WMHeaderProduct")
    public String wMHeaderProduct;
    @JsonProperty("WMHeaderChannel") 
    public String wMHeaderChannel;
    @JsonProperty("WMHeaderTouchpoint") 
    public String wMHeaderTouchpoint;
    @JsonProperty("WMHeaderTxnCorrelID") 
    public String wMHeaderTxnCorrelID;
    @JsonProperty("WMHeaderPayloadType") 
    public String wMHeaderPayloadType;
}
