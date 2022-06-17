package com.confluent.models;
import com.fasterxml.jackson.annotation.JsonProperty;




import java.util.ArrayList;
import java.util.Date;

public class ContextEvent{
    @JsonProperty("EventHeader")
    public EventHeader eventHeader;
    @JsonProperty("SettlCurrency") 
    public String settlCurrency;
    @JsonProperty("WMEventOrigSys") 
    public String wMEventOrigSys;
    @JsonProperty("ExecID") 
    public String execID;
    @JsonProperty("SecondaryTradeID") 
    public String secondaryTradeID;
    @JsonProperty("OrderQtyData") 
    public OrderQtyData orderQtyData;
    @JsonProperty("ExecType") 
    public String execType;
    @JsonProperty("LastQty") 
    public int lastQty;
    @JsonProperty("LastPx") 
    public String lastPx;
    @JsonProperty("Price") 
    public String price;
    @JsonProperty("Instrument") 
    public Instrument instrument;
    @JsonProperty("ContraGrp") 
    public ArrayList<ContraGrp> contraGrp;
    @JsonProperty("SolicitedFlag") 
    public String solicitedFlag;
    @JsonProperty("WMExtRefData") 
    public ArrayList<WMExtRefDatum> wMExtRefData;
    @JsonProperty("TimeInForce") 
    public String timeInForce;
    @JsonProperty("LastCapacity") 
    public String lastCapacity;
    @JsonProperty("LastMkt") 
    public String lastMkt;
    @JsonProperty("Side") 
    public String side;
    @JsonProperty("AllocGrp") 
    public ArrayList<AllocGrp> allocGrp;
    @JsonProperty("MiscFeesGrp") 
    public ArrayList<MiscFeesGrp> miscFeesGrp;
    @JsonProperty("TradeDate") 
    public String tradeDate;
    @JsonProperty("Text") 
    public String text;
    @JsonProperty("TrdRegTimestamps") 
    public ArrayList<TrdRegTimestamp> trdRegTimestamps;
    @JsonProperty("OrdType") 
    public String ordType;
    @JsonProperty("SellerDays") 
    public int sellerDays;
    @JsonProperty("TransactTime") 
    public Date transactTime;
    @JsonProperty("WMBusUnit") 
    public String wMBusUnit;
    @JsonProperty("WMETTrailerCodes") 
    public ArrayList<String> wMETTrailerCodes;
    @JsonProperty("WMActedBy") 
    public String wMActedBy;
    @JsonProperty("PositionQty") 
    public ArrayList<PositionQty> positionQty;
    @JsonProperty("Parties") 
    public ArrayList<Party> parties;
    @JsonProperty("EventTrailer") 
    public EventTrailer eventTrailer;
}
