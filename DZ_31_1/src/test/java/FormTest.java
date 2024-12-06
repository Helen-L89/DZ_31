import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.GeckoDriverService;
//import org.openqa.selenium.firefox.service.GeckoDriverService;

public class FormTest {
    public static void main(String[] args) {
        // Указываем путь к geckodriver (например, geckodriver.exe для Windows)
        System.setProperty("webdriver.gecko.driver", "C:\\work\\geckodriver.exe");

        // Создаем экземпляр драйвера Firefox с опциями
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--fullscreen"); // Для работы в фоновом режиме (опционально)

        // Инициализация FirefoxDriver
        WebDriver driver = new FirefoxDriver(options);

        try {
            // Открытие страницы формы
            driver.get("https://otus.home.kartushin.su/form.html");

            // Заполнение полей формы
            WebElement nameField = driver.findElement(By.name("name"));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement confirmPasswordField = driver.findElement(By.name("confirm_password"));
            WebElement birthDateField = driver.findElement(By.name("birth_date"));
            WebElement languageLevelField = driver.findElement(By.name("language_level"));

            // Заполнение данных
            nameField.sendKeys("Иван Иванов");
            emailField.sendKeys("ivan.ivanov@example.com");
            passwordField.sendKeys("password123");
            confirmPasswordField.sendKeys("password123");
            birthDateField.sendKeys("1990-01-01");
            languageLevelField.sendKeys("Intermediate"); // Можно выбрать любой уровень, зависит от формы

            // Проверка совпадения паролей
            if (!passwordField.getAttribute("value").equals(confirmPasswordField.getAttribute("value"))) {
                System.out.println("Пароли не совпадают!");
                return;
            }

            // Нажатие кнопки отправки
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            // Проверка успешной отправки формы и вывода данных
            WebElement output = driver.findElement(By.id("output")); // Предполагаем, что результаты выводятся в элемент с id "output"
            if (output != null) {
                System.out.println("Данные формы:");
                System.out.println(output.getText());
            } else {
                System.out.println("Не удалось найти вывод данных!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие браузера
            driver.quit();
        }
    }
}

