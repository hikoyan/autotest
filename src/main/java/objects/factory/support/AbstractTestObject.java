package objects.factory.support;

import objects.BasePage;
import objects.factory.TestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Assert;

/**
 * Created by yan on 16/4/29.
 */
public abstract class AbstractTestObject extends BasePage implements TestObject {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String[] configLocations;

    public void setConfigLocations(String... locations) {
        if (locations != null) {
            Assert.noNullElements(locations, "Config locations must not be null");
            this.configLocations = new String[locations.length];
            for (int i = 0; i < configLocations.length; i++) {
                configLocations[i] = locations[i];
            }
        } else {
            this.configLocations = null;
        }
    }

    public String[] getConfigLocations() {
        return configLocations;
    }

    public void start() throws Exception {
        loadTestObjects();
        testStart();
    }

    protected abstract void loadTestObjects() throws Exception;

    protected abstract void testStart() throws Exception;
}
