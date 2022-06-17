package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MiscFeesGrp{
    @JsonProperty("MiscFeeType")
    public String miscFeeType;
    @JsonProperty("MiscFeeAmt") 
    public int miscFeeAmt;
}
