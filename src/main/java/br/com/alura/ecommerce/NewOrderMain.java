package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        Callback callback = (data, err) -> {
            if (err != null) {
                err.printStackTrace();
                return;
            }
            System.out.println("Sucesso ao enviar a mensagem! Na partição: " + data.partition());
        };
        var email = "Welcome Jesus";
        var emailRecord = new ProducerRecord<String, String>("ECOMMERCE_NEW_EMAIL", email);
        producer.send(emailRecord, callback).get();

        for(int i = 0; i < 20; i++) {
            var key = UUID.randomUUID().toString();
            var value = "Lucas, 123456";
            var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", key, value);
            producer.send(record, callback).get();
        }

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
