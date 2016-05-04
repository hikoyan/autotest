package objects.factory.support;

import objects.Locator;
import objects.Locators;
import objects.MutableLocators;
import objects.factory.DriverFactory;
import objects.factory.PageObject;
import objects.factory.xml.DefaultDocumentLoader;
import objects.factory.xml.DocumentLoader;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16/4/29.
 */
public class AbstractXmlTestObject extends AbstractTestObject {

    private PageObject pageObject = new PageObjectDefinition();

    private Locators locators = new MutableLocators();

    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    protected void loadTestObjects() throws Exception {
        String[] configLocations = super.getConfigLocations();
        for (int i = 0; i < configLocations.length; i++) {
            String path = configLocations[i];
            loadTestObjects(path);
        }
    }

    public void loadTestObjects(String path) throws Exception {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        InputSource inputSource = new InputSource(inputStream);
        Document doc = documentLoader.loadDocument(inputSource);
        Element element = doc.getDocumentElement();
        NodeList nl = element.getElementsByTagName("page");
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i) instanceof Element) {
                Element page = (Element) nl.item(i);
                pageObject.setWebDriver(page.getAttribute("web-driver"));
                pageObject.setBaseUrl(page.getAttribute("base-url"));
                NodeList locatorsNL = page.getElementsByTagName("locator");
//                getChildNodes();
                List<Locator> locatorList = new ArrayList<Locator>();
                for (int j = 0; j < locatorsNL.getLength(); j++) {
                    if (locatorsNL.item(j) instanceof Element) {
                        Element locatorEL = (Element) locatorsNL.item(j);
                        Locator locator = new Locator();
                        if (locatorEL.getNodeType() == Node.ELEMENT_NODE) {
                            if ("locator".equals(locatorEL.getNodeName())) {
                                if (null != locatorEL.getAttribute("command")) {
                                    locator.setCommand(locatorEL.getAttribute("command"));
                                }
                                if (null != locatorEL.getAttribute("type")) {
                                    locator.setType(locatorEL.getAttribute("type"));
                                }
                                if (null != locatorEL.getAttribute("target")) {
                                    locator.setTarget(locatorEL.getAttribute("target"));
                                }
                                if (null != locatorEL.getAttribute("value")) {
                                    locator.setValue(locatorEL.getAttribute("value"));
                                }
                                if (null != locatorEL.getAttribute("timeout")) {
                                    locator.setTimeout(locatorEL.getAttribute("timeout"));
                                }
                                locatorList.add(locator);
                            }
                        }
                    }
                }
                locators.setLocators(locatorList);
            }
        }
        pageObject.setLocators((MutableLocators) locators);
    }

    protected void testStart() throws Exception {
        WebDriver driver = DriverFactory.getDriver(pageObject.getWebDriver());
        driver.get(pageObject.getBaseUrl());
        MutableLocators locators = pageObject.getLocators();
        for (int i = 0; i < locators.getLocators().length; i++) {
            Locator locator = locators.getLocators()[i];
            operate(driver, locator);
        }

    }

    public PageObject getPageObject() {
        return pageObject;
    }

    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    public DocumentLoader getDocumentLoader() {
        return documentLoader;
    }

    public void setDocumentLoader(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }
}
