package org.example;

/**
 * Сервис для обработки заказов
 * Использует {@link PaymentProcessor} для обработки платежей
 * Использует {@link NotificationSender} для отправки уведомлений
 */
public class OrderService {
    @AutoInjectable
    private PaymentProcessor paymentProcessor;

    @AutoInjectable
    private NotificationSender notificationSender;

    /**
     * Обработка заказа с заданной суммой и отправка уведомлений
     * @param amount сумма заказа
     * @param message сообщение для уведомления
     */
    public void placeOrder(double amount, String message) {
        paymentProcessor.processPayment(amount);
        notificationSender.sendNotification(message);
    }

    /**
     * Возвращает процессор платежей.
     * @return объект {@link PaymentProcessor}
     */
    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    /**
     * Возвращает отправителя уведомлений
     * @return объект {@link NotificationSender}
     */
    public NotificationSender getNotificationSender() {
        return notificationSender;
    }
}
