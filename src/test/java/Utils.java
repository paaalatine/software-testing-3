import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;
import static org.junit.Assert.fail;

public class Utils {

    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForVisible(WebDriver driver, String xpath) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (driver.findElement(By.xpath(xpath)).isDisplayed()) break; } catch (Exception e) {}
            sleep(1000);
        }
    }

    public void login(WebDriver driver, String username, String password) throws Exception {
        waitForVisible(driver, "//li[@id='current_account']");
        driver.findElement(By.xpath("//li[@id='current_account']")).click();

        waitForVisible(driver, "//input[@name='username']");
        driver.findElement(By.xpath("//input[@name='username']")).clear();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Войти']")).click();
    }

    public void logout(WebDriver driver) throws Exception {
        driver.findElement(By.xpath("//li[@id='current_account']")).click();
        waitForVisible(driver, "//input[@value='Выйти']");
        driver.findElement(By.xpath("//input[@value='Выйти']")).click();
    }
}
