import org.example.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Дополнительные unit-тесты для класса {@link Injector}.
 */
class InjectorTest {

    private static final String CONFIG_FILE = "src/main/resources/test-config.properties";

    /**
     * Подготавливает стандартный конфигурационный файл перед каждым тестом.
     */
    @BeforeEach
    void setup() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("org.example.PaymentProcessor", "org.example.PaypalProcessor");
        properties.setProperty("org.example.NotificationSender", "org.example.EmailNotificationSender");
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Test Configuration");
        }
    }

    /**
     * Тест: Проверка поведения при отсутствии реализации в конфигурации.
     */
    @Test
    void testInjectWithMissingImplementation() throws Exception {
        // Конфигурация с отсутствующей реализацией
        Properties properties = new Properties();
        properties.setProperty("yourpackage.PaymentProcessor", "yourpackage.PaypalProcessor");
        // Убираем NotificationSender
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Missing Implementation Configuration");
        }

        Injector injector = new Injector(CONFIG_FILE);
        OrderService orderService = new OrderService();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            injector.inject(orderService);
        });

        assertTrue(exception.getMessage().contains("No implementation found for interface"),
                "Должно быть исключение для отсутствующего интерфейса");
    }


    /**
     * Тест: Проверка поведения при некорректном классе реализации.
     */
    @Test
    void testInjectWithInvalidClass() throws Exception {
        // Конфигурация с некорректным классом
        Properties properties = new Properties();
        properties.setProperty("org.example.PaymentProcessor", "invalid.ClassName");
        properties.setProperty("org.example.NotificationSender", "org.example.EmailNotificationSender");
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Invalid Class Configuration");
        }

        Injector injector = new Injector(CONFIG_FILE);
        OrderService orderService = new OrderService();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            injector.inject(orderService);
        });

        assertTrue(exception.getCause() instanceof ClassNotFoundException, "Должно быть исключение ClassNotFoundException");
    }

    /**
     * Тест: Проверка поведения при отсутствии файла конфигурации.
     */
    @Test
    void testInjectWithMissingConfigFile() {
        Exception exception = assertThrows(IOException.class, () -> {
            new Injector("nonexistent.properties");
        });

        assertTrue(exception.getMessage().contains("nonexistent.properties"), "Должно быть исключение для отсутствующего файла");
    }

    /**
     * Тест: Проверка поведения при отсутствии аннотации на поле.
     */
    @Test
    void testInjectWithNoAnnotatedFields() throws Exception {
        Injector injector = new Injector(CONFIG_FILE);

        // Класс без аннотаций
        class NoAnnotations {
            private String someField;
        }

        NoAnnotations object = new NoAnnotations();
        NoAnnotations result = injector.inject(object);

        assertNotNull(result, "Объект должен вернуться, даже если нет аннотированных полей");
    }
}
