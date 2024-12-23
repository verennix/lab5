package org.example;

/**
 * Реализация {@link NotificationSender} для отправки уведомлений по электронной почте
 */
public class EmailNotificationSender implements NotificationSender {
    /**
     * Отправляет уведомление по электронной почте
     * @param message текст уведомления
     */
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}
