package objects.factory.support;

import objects.MutableLocators;
import objects.factory.PageObject;

/**
 * Created by yan on 16/4/30.
 */
public class PageObjectDefinition implements PageObject {
    private String webDriver;

    private String baseUrl;

    private MutableLocators locators;

    public String getWebDriver() {
        return this.webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public MutableLocators getLocators() {
        return this.locators;
    }

    public void setLocators(MutableLocators locators) {
        this.locators = locators;
    }
}
