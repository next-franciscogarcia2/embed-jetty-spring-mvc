package com.digitalsanctum.app.web;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;
import java.util.Map;

public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        CloudEnvironment cloudEnvironment = new CloudEnvironment();
        String profile = (cloudEnvironment.isCloudFoundry()) ? "cloud" : "default";
        configurableEnvironment.setActiveProfiles(profile);
        log.info("Active Profile(s) >>> " + Arrays.toString(configurableEnvironment.getActiveProfiles()));

        applicationContext.register(WebConfig.class);

        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        final ServletContextHandler context = new ServletContextHandler();

        context.setErrorHandler(null); // use Spring exception handler(s)
        context.setContextPath("/");
        context.addServlet(servletHolder, "/");

        String webPort = System.getenv("VCAP_APP_PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));

        server.setHandler(context);

        server.start();
        server.join();
    }
}
