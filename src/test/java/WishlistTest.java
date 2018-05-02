import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WishlistTest {
    private String baseUrl;
    private Utils utils;

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        baseUrl = "https://secure.booking.com/mywishlist.ru.html";
    }

    @Test
    public void testRemove() throws Exception {
        //remove(new FirefoxDriver());
        remove(new ChromeDriver());
    }

    private void remove(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        utils.login(driver, "kanukova.s.a@gmail.com", "kekkekkek");
        driver.get(baseUrl);

        utils.waitForVisible(driver, "//*[@id='b2mywishlistPage']/div[3]/div[2]/div[2]/div[3]/div[1]/a[2]");
        driver.findElement(By.xpath("//*[@id='b2mywishlistPage']/div[3]/div[2]/div[2]/div[3]/div[1]/a[2]")).click();

        utils.logout(driver);
        driver.quit();
    }
}
