import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;

import static org.junit.Assert.*;

public class LoginTest {
    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        RemoteControlConfiguration rcc = new RemoteControlConfiguration();
        rcc.setTrustAllSSLCertificates(true);
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://www.booking.com/");
        selenium.start();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        selenium.open("/index.ru.html?label=gen173bo-1DCAEoggJCAlhYSDNYA2iIAYgBAZgBMcIBA3gxMcgBD9gBA-gBAfgBApICAXmYAgKoAgM;sid=6427453015f9a2cd5efc604c63a34afb;sb_price_type=total&");
        selenium.type("//input[@name='username']", "kanukova.s.a@gmail.com");
        selenium.click("name=password");
        selenium.type("name=password", "kekkekkek");
        selenium.click("//input[@value='Войти']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//li[@id='current_account']");
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (selenium.isVisible("//input[@value='Выйти']")) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        selenium.click("//input[@value='Выйти']");
        selenium.waitForPageToLoad("30000");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}