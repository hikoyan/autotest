package test;

import test.core.DriverFactory;
import test.core.UITest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by yan on 16/4/28.
 */
public class LoginTest extends UITest {
    WebDriver driver = DriverFactory.getFirefoxDriver();

    @BeforeMethod(alwaysRun = true)
    public void init() {

        super.init(driver);
        ViewHomePage.setDriver(driver);
    }

    @Test(groups = "loginTest")
    public void loginByUerName() throws Exception {
        ViewHomePage.viewMyProfile();
    }

    @AfterMethod(alwaysRun = true)
    public void stop() {
        super.stop();
    }
}
