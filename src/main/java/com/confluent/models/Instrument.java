package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Instrument{
    @JsonProperty("SecurityType")
    public String securityType;
    @JsonProperty("SecurityID") 
    public String securityID;
    @JsonProperty("SecurityIDSource") 
    public String securityIDSource;
    @JsonProperty("SecAltIDGrp") 
    public ArrayList<SecAltIDGrp> secAltIDGrp;
}
