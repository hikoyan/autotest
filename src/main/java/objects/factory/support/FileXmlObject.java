package objects.factory.support;

/**
 * Created by yan on 16/4/29.
 */
public class FileXmlObject extends AbstractXmlTestObject {

    public FileXmlObject(String configLocation) throws Exception {
        this(new String[]{configLocation});
    }

    public FileXmlObject(String[] configLocations) throws Exception {
        setConfigLocations(configLocations);
    }
}
