package robotricochet.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {

    private static Logger LOGGER = Logger.getAnonymousLogger();

    /**
     * get files values from application.properties
     * @return property
     */

    public static Properties fetchProperties() {

        Properties prop = new Properties();
        String fileName = "application.properties";
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
                LOGGER.info("Loading properties from : " + fileName);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        } else {
            LOGGER.severe("property file '" + fileName + "' not found in the classpath");
        }

        return prop;
    }
}
