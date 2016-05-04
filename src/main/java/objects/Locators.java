package objects;

import java.util.List;

/**
 * Created by yan on 16/4/30.
 */
public interface Locators {

    Locator[] getLocators();

    void setLocators(List<Locator> locators);

    boolean isEmpty();

}
