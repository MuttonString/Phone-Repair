package com.ccsu.servicetask.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    public static void main(String[] args) {
        System.out.println(ConfigUtils.getValue("ip"));
    }

    public static Properties prop = new Properties();

    static {
        try {
            InputStream is = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    public static int getValueForInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}