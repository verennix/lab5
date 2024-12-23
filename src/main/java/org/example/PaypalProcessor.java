package org.example;

/**
 * Реализация {@link PaymentProcessor} для обработки платежей через PayPal.
 */
public class PaypalProcessor implements PaymentProcessor {
    /**
     * Обработка платежей через PayPal
     * @param amount сумма для оплаты
     */
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal.");
    }
}
