package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SecAltIDGrp{
    @JsonProperty("SecurityAltID")
    public String securityAltID;
    @JsonProperty("SecurityAltIDSource") 
    public String securityAltIDSource;
}
