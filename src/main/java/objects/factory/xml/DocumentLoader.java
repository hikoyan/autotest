package objects.factory.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Created by yan on 16/4/29.
 */
public interface DocumentLoader {

    Document loadDocument(InputSource inputSource) throws Exception;
}
