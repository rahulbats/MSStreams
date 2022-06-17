package com.confluent;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MSStreamsTest {
    MSStreams msStreams = new MSStreams();
    private TopologyTestDriver testDriver;
    private TestInputTopic<String, String> inputTopic;
    private TestOutputTopic<String, String> outputTopic;
    private TestOutputTopic<String, String> DLQTopic;
    private String input ="{\n" +
            "\n" +
            "    \"wmBaseEvent\": {\n" +
            "\n" +
            "        \"payloadHeaders\": {\n" +
            "\n" +
            "            \"MESSAGE_SCHEMA\": \"http://wm.morganstanley.com/schema/padt/events/PADTEtrade_v1.json\",\n" +
            "\n" +
            "            \"EVENT_ID\": \"5ca72e67-1ba5-4586-8d3f-5273773bd2f4\",\n" +
            "\n" +
            "            \"EVENT_TYPE\": \"Entity\",\n" +
            "\n" +
            "            \"EVENT_DOMAIN\": \"MutualFundOrder\",\n" +
            "\n" +
            "            \"EVENT_CONTEXT\": \"MutualFundOrder\",\n" +
            "\n" +
            "            \"EVENT_NAME\": \"MFOrder\",\n" +
            "\n" +
            "            \"EVENT_SOURCE\": \"ETS\",\n" +
            "\n" +
            "            \"HEADER_SCHEMA\": \"http://wm.morganstanley.com/schema/events/standardEventHeaders/v1_0_0\",\n" +
            "\n" +
            "            \"EVENT_TIMESTAMP\": \"2021-10-07T20:52:01.000Z\"\n" +
            "\n" +
            "        },\n" +
            "\n" +
            "        \"contextEvent\": {\n" +
            "\n" +
            "            \"EventHeader\": {\n" +
            "\n" +
            "                \"MsgType\": \"AE\",\n" +
            "\n" +
            "                \"SendingTime\": \"2021-10-19T09:06:00.000Z\",\n" +
            "\n" +
            "                \"WMHeader\": {\n" +
            "\n" +
            "                    \"WMHeaderProduct\": \"Equity\",\n" +
            "\n" +
            "                    \"WMHeaderChannel\": \"Retail\",\n" +
            "\n" +
            "                    \"WMHeaderTouchpoint\": \"Kafka\",\n" +
            "\n" +
            "                    \"WMHeaderTxnCorrelID\": \"3fa85f64-5717-4562-b3fc-2c9f66ajfydgh_102140\",\n" +
            "\n" +
            "                    \"WMHeaderPayloadType\": \"MP_JSON_1.1\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            },\n" +
            "\n" +
            "            \"SettlCurrency\": \"USD\",\n" +
            "\n" +
            "            \"WMEventOrigSys\": \"050\",\n" +
            "\n" +
            "            \"ExecID\": \"F9168C5E-CEB2-4faa-B6BF-329BF39FA1E140\",\n" +
            "\n" +
            "            \"SecondaryTradeID\": \"oV1n000aqCR2zFeM0140\",\n" +
            "\n" +
            "            \"OrderQtyData\": {\n" +
            "\n" +
            "                \"OrderQty\": 100\n" +
            "\n" +
            "            },\n" +
            "\n" +
            "            \"ExecType\": \"0\",\n" +
            "\n" +
            "            \"LastQty\": 100,\n" +
            "\n" +
            "            \"LastPx\": \"142.32\",\n" +
            "\n" +
            "            \"Price\": \"142.5\",\n" +
            "\n" +
            "            \"Instrument\": {\n" +
            "\n" +
            "                \"SecurityType\": \"CS\",\n" +
            "\n" +
            "                \"SecurityID\": \"IBM\",\n" +
            "\n" +
            "                \"SecurityIDSource\": \"1C\",\n" +
            "\n" +
            "                \"SecAltIDGrp\": [\n" +
            "\n" +
            "                    {\n" +
            "\n" +
            "                        \"SecurityAltID\": \"459200101\",\n" +
            "\n" +
            "                        \"SecurityAltIDSource\": \"1D\"\n" +
            "\n" +
            "                    },\n" +
            "\n" +
            "                    {\n" +
            "\n" +
            "                        \"SecurityAltID\": \"000039050\",\n" +
            "\n" +
            "                        \"SecurityAltIDSource\": \"1B\"\n" +
            "\n" +
            "                    }\n" +
            "\n" +
            "                ]\n" +
            "\n" +
            "            },\n" +
            "\n" +
            "            \"ContraGrp\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"ContraBroker\": \"MSCO\",\n" +
            "\n" +
            "                   \"ContraTradeQty\": \"0\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"SolicitedFlag\": \"Y\",\n" +
            "\n" +
            "            \"WMExtRefData\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"WMExtRefID\": \"ZZ57592104617191\",\n" +
            "\n" +
            "                    \"WMExtRefIDType\": \"066\",\n" +
            "\n" +
            "                    \"WMExtRefIDSource\": \"049\"\n" +
            "\n" +
            "                },\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"WMExtRefID\": \"42609052021191-1\",\n" +
            "\n" +
            "                    \"WMExtRefIDType\": \"067\",\n" +
            "\n" +
            "                    \"WMExtRefIDSource\": \"050\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"TimeInForce\": \"0\",\n" +
            "\n" +
            "            \"LastCapacity\": \"A\",\n" +
            "\n" +
            "            \"LastMkt\": \"W\",\n" +
            "\n" +
            "            \"Side\": \"1\",\n" +
            "\n" +
            "            \"AllocGrp\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"WMAccountAttribData\": {\n" +
            "\n" +
            "                        \"WMOfficeNo\": \"479\",\n" +
            "\n" +
            "                        \"WMAcctNo\": \"39137\",\n" +
            "\n" +
            "                        \"WMAcctType\": \"1\",\n" +
            "\n" +
            "                        \"WMAcctCode\": \"H\"\n" +
            "\n" +
            "                    },\n" +
            "\n" +
            "                    \"CommissionData\": {\n" +
            "\n" +
            "                        \"CommType\": \"3\",\n" +
            "\n" +
            "                        \"Commission\": 546.45\n" +
            "\n" +
            "                    }\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"MiscFeesGrp\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"MiscFeeType\": \"915\",\n" +
            "\n" +
            "                    \"MiscFeeAmt\": 32\n" +
            "\n" +
            "                },\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"MiscFeeType\": \"907\",\n" +
            "\n" +
            "                    \"MiscFeeAmt\": 17\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"TradeDate\": \"20211018\",\n" +
            "\n" +
            "            \"Text\": \"Done Trade 100@142.32\",\n" +
            "\n" +
            "            \"TrdRegTimestamps\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"TrdRegTimestamp\": \"2021-10-19T09:05:30.000Z\",\n" +
            "\n" +
            "                    \"TrdRegTimestampType\": \"1\",\n" +
            "\n" +
            "                    \"TrdRegTimestampOrigin\": \"034\"\n" +
            "\n" +
            "                },\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"TrdRegTimestamp\": \"2021-10-19T09:03:21.000Z\",\n" +
            "\n" +
            "                    \"TrdRegTimestampType\": \"105\",\n" +
            "\n" +
            "                    \"TrdRegTimestampOrigin\": \"034\"\n" +
            "\n" +
            "                },\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"TrdRegTimestamp\": \"2021-10-19T09:01:17.000Z\",\n" +
            "\n" +
            "                    \"TrdRegTimestampType\": \"106\",\n" +
            "\n" +
            "                    \"TrdRegTimestampOrigin\": \"034\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"OrdType\": \"1\",\n" +
            "\n" +
            "            \"SellerDays\": 2,\n" +
            "\n" +
            "            \"TransactTime\": \"2021-10-19T09:05:30.000Z\",\n" +
            "\n" +
            "            \"WMBusUnit\": \"R\",\n" +
            "\n" +
            "            \"WMETTrailerCodes\": [\n" +
            "\n" +
            "                \"SC\",\n" +
            "\n" +
            "                \"YT\"\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"WMActedBy\": \"03\",\n" +
            "\n" +
            "            \"PositionQty\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"WMSVPCode\": \"0\",\n" +
            "\n" +
            "                    \"WMSVPMethod\": \"\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"Parties\": [\n" +
            "\n" +
            "                {\n" +
            "\n" +
            "                    \"PartyID\": \"232244556\",\n" +
            "\n" +
            "                    \"PartyIDSource\": \"D\",\n" +
            "\n" +
            "                    \"PartyRole\": \"41\",\n" +
            "\n" +
            "                    \"WMPartyIDType\": \"26\"\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            ],\n" +
            "\n" +
            "            \"EventTrailer\": {\n" +
            "\n" +
            "                \"CheckSum\": \"701\"\n" +
            "\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "}";

    @Before
    public void beforeEach() throws Exception {
        Topology topology = msStreams.buildTopology();
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);

        // Create test driver
        testDriver = new TopologyTestDriver(topology, props);
        inputTopic = testDriver.createInputTopic(
                MSStreams.msRawTopic,
                Serdes.String().serializer(),
                Serdes.String().serializer());
        outputTopic = testDriver.createOutputTopic(
                MSStreams.eqTopic,
                Serdes.String().deserializer(),
                Serdes.String().deserializer());
        DLQTopic = testDriver.createOutputTopic(
                MSStreams.deadLetterQueueTopic,
                Serdes.String().deserializer(),
                Serdes.String().deserializer());
    }

    @Test
    public void testTransform(){
        RecordHeaders recordHeaders = new RecordHeaders();
        recordHeaders.add("product_type", "Equity".getBytes(StandardCharsets.UTF_8));
        TestRecord inputRecord = new TestRecord("1234", input,recordHeaders, new Date().getTime());

        inputTopic.pipeInput(inputRecord);
        List<TestRecord<String, String>> outputRecords = outputTopic.readRecordsToList();
        outputRecords.forEach(stringStringTestRecord -> {
            Headers headers =stringStringTestRecord.headers();
            headers.forEach(header -> System.out.println(header.key()+"-"+new String(header.value())));
            System.out.println(stringStringTestRecord.value());
        });
    }

    @Test
    public void testDLQ(){
        RecordHeaders recordHeaders = new RecordHeaders();
        recordHeaders.add("product_type", "invalid".getBytes(StandardCharsets.UTF_8));
        TestRecord inputRecord = new TestRecord("1234", input,recordHeaders, new Date().getTime());

        inputTopic.pipeInput(inputRecord);
        List<TestRecord<String, String>> outputRecords = DLQTopic.readRecordsToList();
        outputRecords.forEach(stringStringTestRecord -> {
            Headers headers =stringStringTestRecord.headers();
            headers.forEach(header -> System.out.println(header.key()+"-"+new String(header.value())));
            System.out.println(stringStringTestRecord.value());
        });
    }
}
