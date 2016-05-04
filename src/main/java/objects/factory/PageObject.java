package objects.factory;

import objects.MutableLocators;

/**
 * Created by yan on 16/4/29.
 */
public interface PageObject {

    String getWebDriver();

    void setWebDriver(String webDriver);

    String getBaseUrl();

    void setBaseUrl(String baseUrl);

    MutableLocators getLocators();

    void setLocators(MutableLocators locators);
}
