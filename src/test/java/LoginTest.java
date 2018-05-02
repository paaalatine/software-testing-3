import java.util.concurrent.TimeUnit;
import org.junit.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {
    private String baseUrl;
    private Utils utils;

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        baseUrl = "https://www.booking.com/ru";
    }

    @Test
    public void testLoginSuccess() throws Exception {
        //loginSuccess(new FirefoxDriver());
        loginSuccess(new ChromeDriver());
    }

    @Test
    public void testLoginFailure() throws Exception {
        //loginFailure(new FirefoxDriver());
        loginFailure(new ChromeDriver());
    }

    private void loginSuccess(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        utils.login(driver, "kanukova.s.a@gmail.com", "kekkekkek");
        sleep(6000);
        utils.logout(driver);
        driver.quit();
    }

    private void loginFailure(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        utils.login(driver, "kanukova.s.a@gmail.com", "kekkek");
        sleep(6000);
        assertEquals(true, utils.isElementPresent(driver, By.cssSelector("div.alert.alert-error.alert-displayed")));
        driver.quit();
    }
 }