package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by yan on 16/4/29.
 */
public class BasePage {

    public void operate(WebDriver driver, Locator locator) {
        WebElement e = findElement(driver, locator);
        if (locator.getCommand().equals("type")) {
            e.sendKeys(locator.getValue());
        } else if (locator.getCommand().equals("click")) {
            e.click();
        }
    }

    public WebElement findElement(WebDriver driver, final Locator locator) {
        final WebElement element = (new WebDriverWait(driver, Long.parseLong
                (locator.getTimeout())))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return getElement(driver, locator);
                    }
                });
        return element;

    }

    public WebElement getElement(WebDriver driver, Locator locator) {
        WebElement e;
        if (locator.getType().equals("xpath")) {
            e = driver.findElement(By.xpath(locator.getTarget()));
        } else if (locator.getType().equals("id")) {
            e = driver.findElement(By.id(locator.getTarget()));
        } else if (locator.getType().equals("name")) {
            e = driver.findElement(By.name(locator.getTarget()));
        } else if (locator.getType().equals("cssSelector")) {
            e = driver.findElement(By.cssSelector(locator.getTarget()));
        } else if (locator.getType().equals("className")) {
            e = driver.findElement(By.className(locator.getTarget()));
        } else if (locator.getType().equals("tagName")) {
            e = driver.findElement(By.tagName(locator.getTarget()));
        } else if (locator.getType().equals("linkText")) {
            e = driver.findElement(By.linkText(locator.getTarget()));
        } else if (locator.getType().equals("partialLinkText")) {
            e = driver.findElement(By.partialLinkText(locator.getTarget()));
        } else {
            e = driver.findElement(By.id(locator.getTarget()));
        }
        return e;
    }
}
