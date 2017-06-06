package org.constantgatherer.crawl;

import org.constantgatherer.model.Gatherer;
import org.constantgatherer.model.GathererType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.constantgatherer.webdriver.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 14:06
 * Copyright Tango Telecom 2014
 */
@Component
public class CrawlManager {

    @Autowired
    CommandExecutor commandExecutor;
    SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();

    private List<Crawler> crawlers = new ArrayList<Crawler>();

    public void startCrawlers(){
        for(Crawler crawler : crawlers){
            runCrawler(crawler);
        }
    }

    public void startCrawlers(List<Gatherer> gatherers){
        for (Gatherer gatherer : gatherers){
            simpleAsyncTaskExecutor.execute(new WebPageCrawlTask(commandExecutor, gatherer));
        }
    }

    public void runCrawler(Crawler crawler){
        simpleAsyncTaskExecutor.execute(crawler);
    }

    public void registerCrawler(Gatherer gatherer){
         if(gatherer.getType().equals(GathererType.SCRAPER)){
             crawlers.add(new WebPageCrawler(gatherer,commandExecutor));
         }
    }

    public void clean(){
        crawlers = new ArrayList<>();
    }
}
