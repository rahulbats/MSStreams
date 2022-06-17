package com.confluent.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllocGrp{
    @JsonProperty("WMAccountAttribData") 
    public WMAccountAttribData wMAccountAttribData;
    @JsonProperty("CommissionData")
    public CommissionData commissionData;
}
