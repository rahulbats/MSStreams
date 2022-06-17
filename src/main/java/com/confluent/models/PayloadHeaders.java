package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PayloadHeaders{
    @JsonProperty("MESSAGE_SCHEMA")
    public String mESSAGE_SCHEMA;
    @JsonProperty("EVENT_ID") 
    public String eVENT_ID;
    @JsonProperty("EVENT_TYPE") 
    public String eVENT_TYPE;
    @JsonProperty("EVENT_DOMAIN") 
    public String eVENT_DOMAIN;
    @JsonProperty("EVENT_CONTEXT") 
    public String eVENT_CONTEXT;
    @JsonProperty("EVENT_NAME") 
    public String eVENT_NAME;
    @JsonProperty("EVENT_SOURCE") 
    public String eVENT_SOURCE;
    @JsonProperty("HEADER_SCHEMA") 
    public String hEADER_SCHEMA;
    @JsonProperty("EVENT_TIMESTAMP") 
    public Date eVENT_TIMESTAMP;
}
