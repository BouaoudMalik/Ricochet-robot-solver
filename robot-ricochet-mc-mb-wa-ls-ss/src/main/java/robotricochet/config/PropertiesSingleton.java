package robotricochet.config;

import java.util.Properties;

public class PropertiesSingleton {

    private static class SingletonHolder {
        /**
         * holds the instance of the Singleton class
         */
        public static final Properties instance = Configuration.fetchProperties();
    }

    /**
     * creating the instance only when someone calls the getInstance() method
     * @return property
     */
    public static Properties getInstance() {

        return SingletonHolder.instance;
    }
}
