package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ContraGrp{
    @JsonProperty("ContraBroker")
    public String contraBroker;
    @JsonProperty("ContraTradeQty") 
    public String contraTradeQty;
}
