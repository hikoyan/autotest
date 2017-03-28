package test;

import test.core.BasePage;
import test.core.Locator;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yan on 16/4/28.
 */
public class LoginPage extends BasePage {

    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage(WebDriver driver) throws Exception {
        super(driver);
        driver.get("https://git.oschina.net/login");
    }

    Locator loginEmailInputBox = new Locator(".//*[@id='user_login']");

    Locator loginPasswordInputBox = new Locator(".//*[@id='user_password']");
    Locator loginButton = new Locator(".//*[@id='new_user']/div[5]/input");
    Locator profile = new Locator(
            "profile");

    public void typeEmailInputBox(String email) throws Exception {
        type(loginEmailInputBox, email);
    }

    public void typePasswordInputBox(String password) throws Exception {
        type(loginPasswordInputBox, password);
    }

    public void clickOnLoginButton() throws Exception {
        click(loginButton);
    }

    public boolean isPrestentProfile() throws IOException {
        return isElementPresent(profile, 20);

    }

    public void waitForPageLoad() {
        super.getDriver().manage().timeouts()
                .pageLoadTimeout(30, TimeUnit.SECONDS);
    }
}
