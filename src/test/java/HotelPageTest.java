import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class HotelPageTest {
    private Utils utils;

    private String BASE_URL = "https://www.booking.com/ru";

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @Test
    public void testSave() throws Exception {
        //save(new FirefoxDriver());
        save(new ChromeDriver());
    }

    private void save(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        utils.login(driver, "kanukova.s.a@gmail.com", "kekkekkek");

        sleep(8000);
        utils.waitForVisible(driver, "//button[@data-sb-id='main']");
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();

        utils.waitForVisible(driver, "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/h3/a");
        driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/h3/a")).click();

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        utils.waitForVisible(driver, "//form[@id='top-book']/button[2]");
        driver.findElement(By.xpath("//form[@id='top-book']/button[2]")).click();

        utils.logout(driver);
        driver.quit();
    }
}
