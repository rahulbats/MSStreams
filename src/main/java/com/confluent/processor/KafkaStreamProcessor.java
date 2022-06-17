package com.confluent.processor;

import com.confluent.models.Root;
import com.confluent.models.WmBaseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.streams.processor.To;
import org.apache.kafka.streams.processor.api.Processor;

import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class KafkaStreamProcessor implements Processor{
    private ProcessorContext context;
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void init(org.apache.kafka.streams.processor.api.ProcessorContext context) {
        this.context=(ProcessorContext) context;
        Processor.super.init(context);
    }

    @Override
    public void process(Record record) {
        Headers headers = this.context.headers();
        Headers recordHeaders = record.headers();
        String  productType="";
        for(Header recordHeader: recordHeaders){
            if(recordHeader.key().equals("product_type")) {
                productType =new String( recordHeader.value());
            }
        }
        if (!(productType.equals("Equity") || productType.equals("FixedIncome") || productType.equals("Syn"))){
            context.forward(record.key(), (String)record.value(),To.child("deadLetterQueue"));
        }
        try {
            JsonNode value = mapper.readTree((String)record.value());
            Root valuePojo = mapper.readValue((String)record.value(), Root.class);
            JsonNode  payloadHeaders = value.get("wmBaseEvent").get("payloadHeaders");
            payloadHeaders.fieldNames().forEachRemaining(key->{
                headers.add(new Header() {
                    @Override
                    public String key() {
                        return key;
                    }

                    @Override
                    public byte[] value() {
                        return payloadHeaders.get(key).asText().getBytes(StandardCharsets.UTF_8);
                    }
                });
            });
            //String productType = value.get("wmBaseEvent").get("contextEvent").get("EventHeader").get("WMHeader").get("WMHeaderProduct").asText();
            context.forward(record.key(), value.get("wmBaseEvent").get("contextEvent").toString(),To.child(productType));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.forward(record.key(), (String)record.value(),To.child("deadLetterQueue"));
        }

    }


    @Override
    public void close() {
        Processor.super.close();
    }
}
