import org.example.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit тесты для класса {@link OrderService}.
 */
class OrderServiceTest {

    /**
     * Тест: Проверка вызова методов PaymentProcessor и NotificationSender.
     */
    @Test
    void testOrderServiceBehavior() {
        // Мок объекты для тестирования
        PaymentProcessor mockPaymentProcessor = amount -> assertEquals(100.0, amount);
        NotificationSender mockNotificationSender = message -> assertEquals("Order completed", message);

        // Устанавливаем моки вручную
        OrderService orderService = new OrderService();
        orderService = new InjectorTestHelper().injectMockDependencies(orderService, mockPaymentProcessor, mockNotificationSender);

        // Проверяем вызовы методов
        orderService.placeOrder(100.0, "Order completed");
    }
}
