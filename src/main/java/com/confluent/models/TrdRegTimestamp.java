package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TrdRegTimestamp{
    @JsonProperty("TrdRegTimestamp")
    public Date trdRegTimestamp;
    @JsonProperty("TrdRegTimestampType") 
    public String trdRegTimestampType;
    @JsonProperty("TrdRegTimestampOrigin") 
    public String trdRegTimestampOrigin;
}
