import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class SearchHotelsTest {

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
    public void testSearchSuccess() throws Exception {
        searchSuccess(new FirefoxDriver());
        //searchSuccess(new ChromeDriver());
    }

    @Test
    public void testSearchFailure() throws Exception {
        searchFailure(new FirefoxDriver());
        //searchFailure(new ChromeDriver());
    }

    private void searchSuccess(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        utils.waitForVisible(driver, "//input[@id='ss']");
        driver.findElement(By.xpath("//input[@id='ss']")).clear();
        driver.findElement(By.xpath("//input[@id='ss']")).sendKeys("Тбилиси");
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();
        driver.quit();
    }

    private void searchFailure(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        utils.waitForVisible(driver, "//button[@data-sb-id='main']");
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();
        assertEquals(true, utils.isElementPresent(driver, By.cssSelector("#destination__error > div")));
        driver.quit();
    }
}

