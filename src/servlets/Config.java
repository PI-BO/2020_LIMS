package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Narwutsch Dominic, Weber Mark
 * created on 08.12.2020
 */
@WebListener
public class Config implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(Config.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Server Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Server Stopped");
    }
}
