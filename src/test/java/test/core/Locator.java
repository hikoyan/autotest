package test.core;

/**
 * Created by yan on 16/4/28.
 */
public class Locator {

    private String element;

    private int waitSec;

    public enum ByType {
        xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
    }

    private ByType byType;

    public Locator() {
    }

    public Locator(String element) {
        this.element = element;
        this.waitSec = 30;
        this.byType = ByType.xpath;
    }

    public Locator(String element, int waitSec) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = ByType.xpath;
    }

    public Locator(String element, int waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
    }

    public String getElement() {
        return element;
    }

    public int getWaitSec() {
        return waitSec;
    }

    public ByType getBy() {
        return byType;
    }

    public void setBy(ByType byType) {
        this.byType = byType;
    }

    public void setReplace(String rep, String rex) {
        StringTools.replaceAll(element, rex, rep);
    }
}
