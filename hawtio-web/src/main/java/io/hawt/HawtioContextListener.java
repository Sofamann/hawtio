package io.hawt;

import io.hawt.jmx.JmxTreeWatcher;
import io.hawt.jmx.PluginRegistry;
import io.hawt.jmx.UploadManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * A {@link javax.servlet.ServletContextListener} which initialises key hawtio services in the webapp
 */
public class HawtioContextListener implements ServletContextListener {

    private JmxTreeWatcher treeWatcher = new JmxTreeWatcher();
    private PluginRegistry registry = new PluginRegistry();
    private UploadManager uploadManager = new UploadManager();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            treeWatcher.init();
            registry.init();
            uploadManager.init();

        } catch (Exception e) {
            throw createServletException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            treeWatcher.destroy();
            registry.destroy();
            uploadManager.destroy();
        } catch (Exception e) {
            throw createServletException(e);
        }
    }

    protected RuntimeException createServletException(Exception e) {
        return new RuntimeException(e);
    }
}
