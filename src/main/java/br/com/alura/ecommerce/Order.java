package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {

    private final String idUser, idOrder;
    private final BigDecimal amount;

    public Order(String idUser, String idOrder, BigDecimal amount) {
        this.idUser = idUser;
        this.idOrder = idOrder;
        this.amount = amount;
    }
}
