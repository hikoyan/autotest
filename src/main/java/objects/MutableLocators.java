package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16/4/30.
 */
public class MutableLocators implements Locators {

    private List<Locator> locatorList;

    public MutableLocators() {
        this.locatorList = new ArrayList<Locator>(0);
    }

    public Locator[] getLocators() {
        return this.locatorList.toArray(new Locator[this.locatorList.size()]);
    }

    public void setLocators(List<Locator> locators) {
        this.locatorList = locators;
    }

    public boolean isEmpty() {
        return this.locatorList.isEmpty();
    }
}
