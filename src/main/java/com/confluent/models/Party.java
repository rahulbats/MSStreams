package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Party{
    @JsonProperty("PartyID")
    public String partyID;
    @JsonProperty("PartyIDSource") 
    public String partyIDSource;
    @JsonProperty("PartyRole") 
    public String partyRole;
    @JsonProperty("WMPartyIDType") 
    public String wMPartyIDType;
}
