package test.core;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yan on 16/4/28.
 */
public class PageFactory {

    public synchronized static Object getPage(Class<?> key, WebDriver d)
            throws ClassNotFoundException, NoSuchMethodException,
            SecurityException, IllegalArgumentException,
            InvocationTargetException {
        String pageClassName = key.getCanonicalName();
        Class<?> clazz = Class.forName(pageClassName);
        Object obj = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(WebDriver.class);
            obj = constructor.newInstance(d);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
