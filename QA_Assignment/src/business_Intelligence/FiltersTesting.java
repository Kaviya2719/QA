package business_Intelligence;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FiltersTesting {

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

            // Test marketplace filter
            testMarketplaceFilter(driver);

            // Test other filters
            testKeywordFilter(driver, 0); // Use index value for keyword
            testRangeFilter(driver, 0); // Use index value for range
            testPositionFilter(driver, 0); // Use index value for position
            testFrequencyFilter(driver, 0); // Use index value for frequency
            testBrandFilter(driver, 0); // Use index value for brand

            // Click "Run" button to apply filters
            clickRunButton(driver);

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
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
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

    public static void testMarketplaceFilter(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement marketplaceDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div/div/div")));
        scrollIntoView(driver, marketplaceDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(marketplaceDropdown);
        selectDropdownOption(driver, By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div/div/div/input"), 0); // Update with actual dropdown XPath
    }

    public static void testKeywordFilter(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement keywordDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchable-dropdown")));
        scrollIntoView(driver, keywordDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(keywordDropdown);
        selectDropdownOption(driver, By.xpath("//xpath/to/keyword/options"), 1); // Update with actual options XPath
    }

    public static void testRangeFilter(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement rangeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div/input")));
        scrollIntoView(driver, rangeDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(rangeDropdown);
        selectDropdownOption(driver, By.xpath("//xpath/to/range/options"), index); // Update with actual options XPath
    }

    public static void testPositionFilter(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement positionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[1]/div[3]/div/div/input")));
        scrollIntoView(driver, positionDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(positionDropdown);
        selectDropdownOption(driver, By.xpath("//xpath/to/position/options"), index); // Update with actual options XPath
    }

    public static void testFrequencyFilter(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement frequencyDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[1]/div[4]/div/div/input")));
        scrollIntoView(driver, frequencyDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(frequencyDropdown);
        selectDropdownOption(driver, By.xpath("//xpath/to/frequency/options"), index); // Update with actual options XPath
    }

    public static void testBrandFilter(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[1]/div[5]/div/div/input")));
        scrollIntoView(driver, brandDropdown);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(brandDropdown);
        selectDropdownOption(driver, By.xpath("//xpath/to/brand/options"), index); // Update with actual options XPath
    }

    public static void clickRunButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[1]/button")));
        scrollIntoView(driver, runButton);
        waitForOverlayToDisappear(driver);

        clickElementWithRetry(runButton);
    }

    private static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private static void waitForOverlayToDisappear(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("_overlay_gipuj_7")));
    }

    private static void clickElementWithRetry(WebElement element) {
        for (int i = 0; i < 3; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                // Wait and retry if the click fails
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private static void selectDropdownOption(WebDriver driver, By dropdownXPath, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate and click the dropdown to open it
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownXPath));
            scrollIntoView(driver, dropdown);
            dropdown.click(); // Open the dropdown

            // Wait for options to be visible
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//xpath/to/options")));
            
            // Validate the index and click the option
            if (index >= 0 && index < options.size()) {
                WebElement option = options.get(index);
                scrollIntoView(driver, option);
                option.click(); // Click the option by index
            } else {
                System.out.println("Index out of bounds: " + index);
            }
        } catch (Exception e) {
            System.out.println("Error selecting dropdown option: " + e.getMessage());
        }
    }
}
