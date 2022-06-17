package com.confluent.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommissionData{
    @JsonProperty ("CommType")
    public String commType;
    @JsonProperty("Commission")
    public double commission;
}
