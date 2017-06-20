package ua.com.rozetka.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class PropertiesCache {

    private final Properties configProp = new Properties();

    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private PropertiesCache() {
        //Private constructor to restrict new instances
        LOG.info("Read all properties from file");
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            LOG.error("Cann't read all properties from file. Exception: " + e);
        }
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    //Bill Pugh Solution for singleton pattern
    //initialization-on-demand holder idiom
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

}
