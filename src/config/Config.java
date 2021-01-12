package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

/**
 * @author Narwutsch Dominic, Weber Mark
 * created on 08.12.2020
 */
@WebListener
public class Config implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(Config.class.getName());
    private static final String PROPERTIES_PATH = "/WEB-INF/classes/application.properties";
    private static Properties props;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Server Started");
        getApplicationProperties();
        addProperties("hi", "ho");
        saveProperties();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Server Stopped");
        // This manually deregisters JDBC driver, which prevents Tomcat 9 from complaining about memory leaks wrto this class
/*        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.debug(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.debug(String.format("Error deregistering driver %s", driver), e);
            }
        }*/

        Driver mariadbDriver = null;
        try {
            mariadbDriver = DriverManager.getDriver("jdbc:mariadb://localhost:3306/demo");
            DriverManager.deregisterDriver(mariadbDriver);
            logger.debug(String.format("deregistering jdbc driver: %s", mariadbDriver));
        } catch (SQLException e) {
            logger.error(String.format("Error deregistering driver %s", mariadbDriver), e);
        }
    }

    /**
     * Loads Properties from File
     * @return boolean loading successful
     */
    public static Boolean loadProperties() {
        props = new Properties();
        try (InputStream is = getServletContext().getResourceAsStream(PROPERTIES_PATH)) {
            props.load(is);
            logger.debug("Loaded Properties");
            return true;
        } catch (IOException e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * Loads Properties from File if null
     * @return new set of Properties
     */
    public static Properties getApplicationProperties() {
        if (props == null && loadProperties()) return props;
        return props;
    }

    /**
     * Add value to Properties
     * @return altered set of Properties
     */
    public static Properties addProperties(String key, String value) {
        props.setProperty(key, value);
        return props;
    }

    /**
     * Save Properties to File
     */
    private static void saveProperties() {
        try (FileOutputStream fr = new FileOutputStream(
                getServletContext().getResource(PROPERTIES_PATH).getFile().replace("%20", " ")
        )) {
            props.store(fr, null);
            logger.info("After saving properties: " + props);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    
    public static String getValue(Object key){
    	
    	return Config.getApplicationProperties().get(key).toString();
    }
}
