package test.core;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by yan on 16/4/28.
 */
public class XmlUtils {

    public static HashMap<String, Locator> readXMLDocument(String path,
                                                           String pageName) throws Exception {

        HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
        locatorMap.clear();
        File file = new File(path);
        if (!file.exists()) {
            return locatorMap = null;
            //throw new IOException("Can't find " + path);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        for (Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
            Element page = (Element) i.next();
            if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
                for (Iterator<?> l = page.elementIterator(); l.hasNext(); ) {
                    String type = null;
                    String timeOut = "3";
                    String value = null;
                    String locatorName = null;
                    Element locator = (Element) l.next();
                    for (Iterator<?> j = locator.attributeIterator(); j
                            .hasNext(); ) {
                        Attribute attribute = (Attribute) j.next();
                        if (attribute.getName().equals("type")) {
                            type = attribute.getValue();
                        } else if (attribute.getName().equals("timeOut")) {
                            timeOut = attribute.getValue();
                        } else {
                            value = attribute.getValue();
                        }

                    }
                    Locator temp = new Locator(value, Integer.parseInt(timeOut), getByType(type));
                    locatorName = locator.getText();
                    locatorMap.put(locatorName, temp);
                }
                continue;
            }

        }
        return locatorMap;

    }

    /**
     * @param type
     */
    public static Locator.ByType getByType(String type) {
        Locator.ByType byType = Locator.ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = Locator.ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = Locator.ByType.id;
        } else if (type.equalsIgnoreCase("linkText")) {
            byType = Locator.ByType.linkText;
        } else if (type.equalsIgnoreCase("name")) {
            byType = Locator.ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = Locator.ByType.className;
        } else if (type.equalsIgnoreCase("cssSelector")) {
            byType = Locator.ByType.cssSelector;
        } else if (type.equalsIgnoreCase("partialLinkText")) {
            byType = Locator.ByType.partialLinkText;
        } else if (type.equalsIgnoreCase("tagName")) {
            byType = Locator.ByType.tagName;
        }
        return byType;
    }

    public static void writeXMLDocument() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter("output.xml"), format);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("map");
        root.addComment("locator of page map info");
        Element page = root.addElement("page").addAttribute("pageName",
                "com.dbyl.libarary.pageAction.HomePage");
        page.addComment("Locator lists");
        page.addElement("locator").addAttribute("type", "ByName")
                .addAttribute("timeOut", "3")
                .addAttribute("value", "\\\\div[@name]").addText("loginButton");
        page.addElement("locator").addAttribute("type", "ById")
                .addAttribute("timeOut", "3")
                .addAttribute("value", "\\\\div[@id]").addText("InputButton");
        writer.write(document);
        writer.close();
    }
}
