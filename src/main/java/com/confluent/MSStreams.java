package com.confluent;

import com.confluent.processor.KafkaStreamProcessor;

import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class MSStreams {
    public static final String msRawTopic = "msRawTopic";
    public static final String eqTopic = "eqTopic";
    public static final String synTopic = "synTopic";
    public static final String fiTopic = "fiTopic";
    public static final String deadLetterQueueTopic = "deadLetterQueueTopic";
    public static KafkaStreams streams = null;
    static Properties getStreamsConfig() throws IOException {
        final Properties props = new Properties();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");
        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "msconsumer");
        props.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"\" password=\"\";");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-43n10.us-central1.gcp.confluent.cloud:9092");
        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        return props;
    }


    public static Topology buildTopology(){
        StreamsBuilder kStreamBuilder = new StreamsBuilder();


        Topology topology =  kStreamBuilder.build();
        topology.addSource("Source", msRawTopic)
                .addProcessor("process", ()->new KafkaStreamProcessor(), "Source")
                .addSink("Equity",eqTopic, "process")
                .addSink("FixedIncome",fiTopic, "process")
                .addSink("Syn",synTopic, "process")
                .addSink("deadLetterQueue",deadLetterQueueTopic, "process");
        return topology;
    }
    public static void main(String args[]) throws IOException {
        final Properties props = getStreamsConfig();

        Topology topology = buildTopology();

        streams = new KafkaStreams(topology, props);

        final CountDownLatch latch = new CountDownLatch(1);

        // attach shutdown handler to catch control-c
        Runtime.getRuntime().addShutdownHook(new Thread("rx-stream-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            CompletableFuture<KafkaStreams.State> stateFuture = new CompletableFuture<>();
            //streams.state()
            streams.setStateListener((newState, oldState) -> {
                if (stateFuture.isDone()) {
                    return;
                }

                if (newState == KafkaStreams.State.RUNNING || newState == KafkaStreams.State.ERROR) {
                    stateFuture.complete(newState);
                }
            });
            //streams.cleanUp();
            streams.start();
            try {
                KafkaStreams.State finalState = stateFuture.get();
                if (finalState == KafkaStreams.State.RUNNING) {
                    System.out.println(KafkaStreams.State.RUNNING);
                    //deDupStream(streams);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }

            latch.await();
        } catch (final Throwable e) {
            System.exit(1);
        }
        System.exit(0);

    }
}
