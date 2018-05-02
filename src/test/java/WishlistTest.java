import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class WishlistTest {
    private Utils utils;
    private String BASE_URL = "https://www.booking.com/ru";

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @Test
    public void testRemove() throws Exception {
        //remove(new FirefoxDriver());
        remove(new ChromeDriver());
    }

    private void remove(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        utils.login(driver, "kanukova.s.a@gmail.com", "kekkekkek");

        sleep(7000);

        driver.findElement(By.xpath("//li[@id='current_account']")).click();
        driver.findElement(By.cssSelector("div.profile-menu__item.profile_menu__item--wishlists")).click();

        utils.waitForVisible(driver, "//*[@id='b2mywishlistPage']/div[3]/div[2]/div[2]/div[2]/div[1]/a[2]/i");
        driver.findElement(By.xpath("//*[@id='b2mywishlistPage']/div[3]/div[2]/div[2]/div[2]/div[1]/a[2]/i")).click();

        utils.logout(driver);
        driver.quit();
    }
}
