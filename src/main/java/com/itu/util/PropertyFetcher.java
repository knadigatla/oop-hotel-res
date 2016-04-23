package com.itu.util;

import java.io.*;
import java.util.Properties;

public class PropertyFetcher {

    private Properties properties;

    public PropertyFetcher(File fd)  {
        try {
            properties = new Properties();
            InputStream inputStream =  new FileInputStream(fd);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
}
