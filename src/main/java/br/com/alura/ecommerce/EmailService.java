package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {

    public static void main(String[] args) {
        var emailService = new EmailService();
        try (var service = new KafkaService(EmailService.class.getSimpleName(), "ECOMMERCE_NEW_EMAIL", emailService::parse)) {
            service.run();
        }

    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("Send Email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            System.out.println("Processing!");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email send");
    }
}