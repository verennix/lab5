package org.example;

/**
 * Интерфейс для обработки платежей
 */
public interface PaymentProcessor {
    /**
     * Обрабатывает платеж
     * @param amount сумма для оплаты
     */
    void processPayment(double amount);
}
