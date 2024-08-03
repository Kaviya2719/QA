package business_Intelligence;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\seleniumFiles\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Open the login page
            driver.get("https://test.anarix.ai/market-intelligence?marketplace=amazon");

            // Perform login
            performLogin(driver, "qa-assessment@mailinator.com", "NapQA@2023");

            // Verify login
            verifyLogin(driver);

        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void performLogin(WebDriver driver, String username, String password) {
        // Locate username field and enter username
        WebElement usernameField = driver.findElement(By.id("email"));
        usernameField.sendKeys(username);

        // Locate password field and enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\":r2:\"]"));
        loginButton.click();
    }

    public static void verifyLogin(WebDriver driver) {
        // Wait for the element that indicates a successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String expectedTitle = "Anarix";
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        if (driver.getTitle().equals(expectedTitle)) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
    }
}
