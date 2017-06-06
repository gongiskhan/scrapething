package org.constantgatherer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ggomes on 28/05/14.
 */
@Configuration
@ComponentScan({
        "org.constantgatherer.crawl",
        "org.constantgatherer.error",
        "org.constantgatherer.model",
        "org.constantgatherer.persistence",
        "org.constantgatherer.service",
        "org.constantgatherer.util",
        "org.constantgatherer.webdriver"
})
@Import(PersistenceConfig.class)
public class ServiceConfig {}
