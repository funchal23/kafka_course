package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (var orderDispatcher = new KafkaDispatcher<Order>()) {
            try (var emailDispatcher = new KafkaDispatcher<String>()) {

                for (int i = 0; i < 10; i++) {
                    var email = "Welcome Jesus";
                    emailDispatcher.send("ECOMMERCE_NEW_EMAIL", email, email);

                    var idUser = UUID.randomUUID().toString();
                    var idOrder = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 * 1);
                    var order = new Order(idUser, idOrder, amount);
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", idUser, order);
                }
            }
        }
    }
}
