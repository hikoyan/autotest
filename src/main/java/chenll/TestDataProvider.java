package chenll;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
    private List l;

    public TestDataProvider() {
        this.getXmlData();
    }

    public void getXmlData(){
        ParserXml p = new ParserXml();
        l = p.parser3Xml(new File("src/main/java/chenll/TestData.xml")
                .getAbsolutePath());
    }

    @DataProvider
    public Object[][] providerMethod(Method method){
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (int i = 0; i < l.size(); i++) {
            Map m = (Map) l.get(i);
            if(m.containsKey(method.getName())){
                Map<String, String> dm = (Map<String, String>) m.get(method.getName());
                result.add(dm);
            }
        }
        Object[][] files = new Object[result.size()][];
        for(int i=0; i<result.size(); i++){
            files[i] = new Object[]{result.get(i)};
        }

        Object[] f = new Object[2];
        f[0] = new Object();
        return files;
    }

    @Test(dataProvider="providerMethod")
    public void testmethod1(Map<?, ?> param){
        System.out.println("method1 received:"+param.get("input"));
    }

    @Test(dataProvider="providerMethod")
    public void testmethod2(Map<?, ?> param){
        System.out.println("method2 received:"+param.get("input"));
    }

    @Test(dataProvider="providerMethod")
    public void testmethod3(Map<?, ?> param){
        System.out.println("method3 received:"+param.get("input"));
    }

    @Test
    public void testmethod4(){
        System.out.println("method4 received:4");
    }

}