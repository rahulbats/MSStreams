package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WMAccountAttribData{
    @JsonProperty("WMOfficeNo")
    public String wMOfficeNo;
    @JsonProperty("WMAcctNo") 
    public String wMAcctNo;
    @JsonProperty("WMAcctType") 
    public String wMAcctType;
    @JsonProperty("WMAcctCode") 
    public String wMAcctCode;
}
