package objects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by yan on 16/5/3.
 */
public class DriverFactory {

    public static WebDriver getDriver(String arg) {
        WebDriver driver = null;
        if (arg.equals("chromedriver")) {
            driver = new ChromeDriver();
        } else if (arg.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (arg.equals("ie")) {
            driver = new InternetExplorerDriver();
        } else if (arg.equals("edge")) {
            driver = new EdgeDriver();
        }
        return driver;
    }
}
