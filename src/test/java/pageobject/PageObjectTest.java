package pageobject;

import objects.factory.TestObject;
import objects.factory.support.FileXmlObject;
import org.testng.annotations.Test;

/**
 * Created by yan on 16/4/29.
 */
public class PageObjectTest {

    @Test
    public void test() throws Exception {
        TestObject testObject = new FileXmlObject("/Users/yan/Workspace/My/autotest/src/test/resources/testobject.xml");
        testObject.start();
    }
}
