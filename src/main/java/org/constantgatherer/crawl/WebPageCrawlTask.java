package org.constantgatherer.crawl;

import org.constantgatherer.model.Gatherer;
import org.constantgatherer.webdriver.CommandExecutor;

/**
 * User: ggomes
 * Date: 14-01-2014
 * Time: 9:11
 * Copyright Tango Telecom 2014
 */
public class WebPageCrawlTask implements Runnable {

    CommandExecutor commandExecutor;
    Gatherer gatherer;

    public WebPageCrawlTask(CommandExecutor commandExecutor, Gatherer gatherer){
        this.commandExecutor = commandExecutor;
        this.gatherer = gatherer;
    }

    @Override
    public void run() {
        Crawler crawler = new WebPageCrawler(this.gatherer, commandExecutor);
        crawler.run();
    }
}
