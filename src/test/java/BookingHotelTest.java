import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class BookingHotelTest {
    private Utils utils;

    private String BASE_URL = "https://www.booking.com/index.ru.html";

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @Test
    public void testBookSuccess() throws Exception {
        //bookSuccess(new FirefoxDriver());
        //bookSuccess(new ChromeDriver());
    }

    @Test
    public void testBookFailure() throws Exception {
        //bookFailure(new FirefoxDriver());
        bookFailure(new ChromeDriver());
    }

    private void bookSuccess(WebDriver driver) throws Exception {

        goToBookingPage(driver);
        driver.findElement(By.xpath("//*[@id='firstname']")).clear();
        driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("Sofia");

        driver.findElement(By.xpath("//*[@id='lastname']")).clear();
        driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("Kanukova");

        driver.findElement(By.xpath("//*[@id='email']")).clear();
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("kanukova.s.a@gmail.com");

        driver.findElement(By.xpath("//*[@id='email_confirm']")).clear();
        driver.findElement(By.xpath("//*[@id='email_confirm']")).sendKeys("kanukova.s.a@gmail.com");

        driver.findElement(By.xpath("//*[@id='b_tt_holder_2']/button")).click();

        driver.quit();
    }

    private void bookFailure(WebDriver driver) throws Exception {
        goToBookingPage(driver);
        driver.findElement(By.xpath("//*[@id='b_tt_holder_2']/button")).click();
        assertEquals(true, utils.isElementPresent(driver, By.cssSelector("#bookOverviewTop > div.top-warning.top-warning__m-type-errors.bp_top_validation_errors > div > span")));
        driver.quit();
    }

    private void goToBookingPage(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        utils.waitForVisible(driver, "//input[@id='ss']");
        driver.findElement(By.xpath("//input[@id='ss']")).clear();
        driver.findElement(By.xpath("//input[@id='ss']")).sendKeys("Тбилиси");

        //DATES
        driver.findElement(By.xpath("//*[@id='frm']/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/button")).click();

        utils.waitForVisible(driver, "//*[@id='frm']/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[2]/td[3]/span");
        driver.findElement(By.xpath("//*[@id='frm']/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[2]/td[3]/span")).click();

        driver.findElement(By.xpath("//*[@id='frm']/div[1]/div[2]/div/div[3]/div/div/div/div[1]/div/button")).click();

        utils.waitForVisible(driver, "//*[@id='frm']/div[1]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[3]/td[3]/span");
        driver.findElement(By.xpath("//*[@id='frm']/div[1]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[3]/td[3]/span")).click();

        //GO
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();

        //SELECTION HOTEL
        utils.waitForVisible(driver, "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/h3/a");
        driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/h3/a")).click();

        //ANOTHER TAB
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        //BOOKING

        sleep(5000);

        utils.waitForVisible(driver, "//*[@id='hp_book_now_button']");
        driver.findElement(By.xpath("//*[@id='hp_book_now_button']")).click();

        sleep(5000);

        driver.findElement(By.xpath("//*[@id='hprt-form']/table/tbody/tr[1]/td[6]/div/div[4]/div[2]")).click();
        sleep(5000);
    }
}
