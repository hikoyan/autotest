package test;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class test1 {

    public static void main(String[] args) {


        try {
            Configuration config = new PropertiesConfiguration("config/driver.properties");
            String s = String.valueOf(config.getProperties("chromedriver_win"));
            System.out.println(s);
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//  String s = test2.getTestData("config/driver.properties", "chromedriver_win");
//  System.out.println(s);
    }

// public static String getTestData(String configFilePath, String key) {
//  Configuration config = null;
//  try {
//   config = new PropertiesConfiguration(configFilePath);
//  } catch (ConfigurationException e) {
//   e.printStackTrace();
//  }
//
//  return String.valueOf(config.getProperty(key));
//
// }

}