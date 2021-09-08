import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class BasicProducer {
    public static void main(String[] args) {
        System.out.println("=====Starting Basic Producer=====");

        Properties settings = new Properties();
        settings.put("client.id", "basic-producer-v0.0.1");
        settings.put("bootstrap.servers", "localhost:9092");
        settings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        settings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final KafkaProducer<String, String> producer = new KafkaProducer<>(settings);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("=====Stopping Basic Producer====");
        }));

        final String topic = "hello_world_topic";

        for (int i=1; i <= 1000; i++) {
            final String key = "k_" + i;
            final String value = "v_" + i;
            final ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record);
        }
    }
}
