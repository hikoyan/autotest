package test;

import test.core.BasePage;
import test.core.Locator;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by yan on 16/4/28.
 */
public class HomePage extends BasePage {

    private Locator profile = new Locator(".//*[@id='_easyui_tree_15']/span[4]");
    private Locator myMainPage = new Locator(".//*[@id='_easyui_tree_13']/span[4]");

    public HomePage(WebDriver driver) throws Exception {
        super(driver);
    }

    public void clickOnMyProfile() throws Exception {
        click(profile);
    }

    public void clickAndHoldProfile() throws IOException {
        clickAndHold(profile);
    }

    public void clickOnMainPage() throws Exception {
//        clickAndHoldProfile();
        click(myMainPage);
    }
}
