package org.example;

/**
 *Интерфейс для отправки уведомлений
 */
public interface NotificationSender {
    /**
     * Отправляет уведомление
     * @param message текст уведомления
     */
    void sendNotification(String message);
}
