package org.example;

/**
 * Главный класс для запуска приложения.
 * Демонстрирует использование {@link Injector} для автоматического внедрения зависимостей
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Указываем путь к файлу конфигурации
            Injector injector = new Injector("src/main/resources/config.properties");

            // Создаем объект с аннотированными зависимостями
            OrderService orderService = injector.inject(new OrderService());

            // Используем внедренные зависимости
            orderService.placeOrder(500.0, "Payment successful for your order!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
