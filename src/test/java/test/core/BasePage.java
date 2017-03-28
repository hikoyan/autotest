package test.core;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yan on 16/4/28.
 */
public class BasePage {

    protected WebDriver driver;

    HashMap<String, Locator> locatorMap;

    String path;

    protected BasePage(WebDriver driver) throws Exception {
        this.driver = driver;
        // locatorMap = ReadExcelUtil.getLocatorMap();
        path = System.getProperty("user.dir")
                + "/src/com/dbyl/libarary/pageAction/"
                + this.getClass().getSimpleName() + ".xml";
        locatorMap = XmlUtils.readXMLDocument(path, this.getClass()
                .getCanonicalName());
    }

    protected void type(Locator locator, String values) throws Exception {
        WebElement e = findElement(driver, locator);
        e.sendKeys(values);
    }

    protected void typeQuick(Locator locator, String values) throws Exception {
        WebElement e = findElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=\"" + values + "\"", e);

    }

    protected void setRichTextBox(Locator locator, String text) {
        WebElement e = findElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
    }

    protected String getRichTextBox(Locator locator, String text) {
        WebElement e = findElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = (String) js.executeScript(
                "arguments[0].getInnerHTML()", e);
        return result;
    }

    protected void scrollToElement(Locator locator) {
        WebElement e = findElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // roll down and keep the element to the center of browser
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
    }

    protected void click(Locator locator) throws Exception {
        WebElement e = findElement(driver, locator);
        e.click();
    }

    protected void select(Locator locator, String value) throws Exception {
        WebElement e = findElement(driver, locator);
        Select select = new Select(e);

        try {
            select.selectByValue(value);
        } catch (Exception notByValue) {
            select.selectByVisibleText(value);
        }
    }

    protected void alertConfirm() throws Exception {
        Alert alert = driver.switchTo().alert();
        try {
            alert.accept();
        } catch (Exception notFindAlert) {
            throw notFindAlert;
        }
    }

    protected void alertDismiss() throws Exception {
        Alert alert = driver.switchTo().alert();
        try {
            alert.dismiss();
        } catch (Exception notFindAlert) {
            throw notFindAlert;
        }
    }

    protected String getAlertText() throws Exception {
        Alert alert = driver.switchTo().alert();
        try {
            return alert.getText();
        } catch (Exception notFindAlert) {
            throw notFindAlert;
        }
    }

    protected void clickAndHold(Locator locator) throws IOException {
        WebElement e = findElement(driver, locator);
        Actions actions = new Actions(driver);
        actions.clickAndHold(e).perform();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(Locator locator) throws IOException {
        return getElement(this.getDriver(), locator);
    }

    public WebElement getElement(WebDriver driver, Locator locator)
            throws IOException {
        locator = getLocator(locator.getElement());
        WebElement e;
        switch (locator.getBy()) {
            case xpath:
                e = driver.findElement(By.xpath(locator.getElement()));
                break;
            case id:
                e = driver.findElement(By.id(locator.getElement()));
                break;
            case name:
                e = driver.findElement(By.name(locator.getElement()));
                break;
            case cssSelector:
                e = driver.findElement(By.cssSelector(locator.getElement()));
                break;
            case className:
                e = driver.findElement(By.className(locator.getElement()));
                break;
            case tagName:
                e = driver.findElement(By.tagName(locator.getElement()));
                break;
            case linkText:
                e = driver.findElement(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                e = driver.findElement(By.partialLinkText(locator.getElement()));
                break;
            default:
                e = driver.findElement(By.id(locator.getElement()));
        }
        return e;
    }

    public boolean isElementPresent(WebDriver driver, Locator myLocator,
                                    int timeOut) throws IOException {
        final Locator locator = getLocator(myLocator.getElement());
        boolean isPresent = false;
        WebDriverWait wait = new WebDriverWait(driver, 60);
        isPresent = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return findElement(d, locator);
            }
        }).isDisplayed();
        return isPresent;
    }

    public boolean isElementPresent(Locator locator, int timeOut)
            throws IOException {
        return isElementPresent(driver, locator, timeOut);
    }

    public WebElement findElement(WebDriver driver, final Locator locator) {
        WebElement element = (new WebDriverWait(driver, locator.getWaitSec()))
                .until(new ExpectedCondition<WebElement>() {

                    public WebElement apply(WebDriver driver) {
                        try {
                            return getElement(driver, locator);
                        } catch (IOException e) {
                            return null;
                        }

                    }

                });
        return element;

    }

    public Locator getLocator(String locatorName) throws IOException {

        Locator locator = new Locator(locatorName);
        // for (int i = 0; i < locatorMap.length; i++) {
        // if (locatorMap[i][0].endsWith(locatorName)) {
        // return locator = new Locator(locatorMap[i][1]);
        // }
        // }
        // return locator = new Locator(locatorName);
        if (locatorMap != null) {
            locator = locatorMap.get(locatorName);
        }
        return locator;
    }

    public int open(String URL) {
        if (URL == null || URL.equals("")) {
            return -1;
        }
        int responseStatus = 200;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(URL);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStatus;
    }

    public void DOMEvent(Locator locator, String event) {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        String js = "var event;if (document.createEvent){event = document.createEvent(\"HTMLEvents\");event.initEvent(\"" + event + "\", true, false);arguments[0].dispatchEvent(event);} else {arguments[0].fireEvent(\"on" + event + "\")}";
        jse.executeScript(js, findElement(driver, locator));
    }
}
