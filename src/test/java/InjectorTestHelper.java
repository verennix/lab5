import org.example.*;

import java.lang.reflect.Field;

/**
 * Вспомогательный класс для упрощения тестирования.
 */
public class InjectorTestHelper {

    /**
     * Внедряет зависимости вручную для тестирования.
     *
     * @param orderService      объект {@link OrderService}.
     * @param paymentProcessor  объект {@link PaymentProcessor}.
     * @param notificationSender объект {@link NotificationSender}.
     * @return объект {@link OrderService} с внедренными зависимостями.
     */
    public OrderService injectMockDependencies(OrderService orderService, PaymentProcessor paymentProcessor,
                                               NotificationSender notificationSender) {
        try {
            Field paymentProcessorField = OrderService.class.getDeclaredField("paymentProcessor");
            paymentProcessorField.setAccessible(true);
            paymentProcessorField.set(orderService, paymentProcessor);

            Field notificationSenderField = OrderService.class.getDeclaredField("notificationSender");
            notificationSenderField.setAccessible(true);
            notificationSenderField.set(orderService, notificationSender);
        } catch (Exception e) {
            throw new RuntimeException("Error injecting mock dependencies", e);
        }

        return orderService;
    }
}
