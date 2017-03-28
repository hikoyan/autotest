package test;

import test.core.PageFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by yan on 16/4/28.
 */
public class CommonLogin {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    static LoginPage loginPage;

    public static HomePage login(String email, String password)
            throws Exception {
        loginPage = new LoginPage(getDriver());
        loginPage.waitForPageLoad();
        loginPage.typeEmailInputBox(email);
        loginPage.typePasswordInputBox(password);
        loginPage.clickOnLoginButton();
//        Assert.assertTrue(loginPage.isPrestentProfile(), "login failed");

        return (HomePage) PageFactory.getPage(HomePage.class, getDriver());
    }

    public static HomePage login() throws Exception {
        return CommonLogin.login("yy163email@163.com", "Yan123123");
    }

    public static void setDriver(WebDriver driver) {
        CommonLogin.driver = driver;
    }
}
