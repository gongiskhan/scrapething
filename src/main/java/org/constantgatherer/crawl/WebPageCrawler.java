package org.constantgatherer.crawl;

import org.constantgatherer.model.GatheredData;
import org.constantgatherer.model.Gatherer;
import org.constantgatherer.model.GathererFragment;
import org.constantgatherer.webdriver.CommandExecutor;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 20:44
 * Copyright Tango Telecom 2014
 */
public class WebPageCrawler implements Crawler{

    CommandExecutor commandExecutor;
    Gatherer gatherer;
    GatheredData gatheredData;

    public WebPageCrawler(Gatherer gatherer, CommandExecutor commandExecutor){
        this.gatherer = gatherer;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public synchronized void run() {
        gatherData();
    }

    @Override
    public synchronized void gatherData() {
        gatheredData = new GatheredData();
        gatheredData.setName(this.gatherer.getName());
        for(GathererFragment gathererFragment : gatherer.getFragments()){
            gatheredData.getFragments().put(gathererFragment.getName(),commandExecutor.executeCommandsAndGetText(gathererFragment.getCommands()));
        }
        publishData();
    }

    @Override
    public synchronized void publishData() {
        System.out.println("publish data: "+ gatheredData.getFragments().get("testData"));
        if(gatheredData.getFragments().size() > 0){
            System.out.println("Time: "+ gatheredData.getFragments().get("time"));
        }
    }
}
