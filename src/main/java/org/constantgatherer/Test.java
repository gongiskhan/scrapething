package org.constantgatherer;

import org.constantgatherer.model.Gatherer;
import org.constantgatherer.model.GathererFragment;
import org.constantgatherer.model.GathererType;
import org.constantgatherer.model.webdriver.Command;
import org.constantgatherer.model.webdriver.CommandType;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 23:07
 * Copyright Tango Telecom 2014
 */
public class Test {
    public static Gatherer createMockSpotConfig(){

        String rnd = String.valueOf(Math.random() * 1000);

        Gatherer gatherer = new Gatherer();
        gatherer.setName("world_clock"+rnd);
        gatherer.setType(GathererType.SCRAPER);
        GathererFragment gathererFragment = new GathererFragment();
        gathererFragment.setVisibility("static");
        gathererFragment.setName("time");
        gatherer.getFragments().add(gathererFragment);
        Command command1 = new Command();
        command1.setCommandType(CommandType.OPEN);
        command1.setTarget("http://www.timeanddate.com/worldclock/city.html?n=4");
        Command command2 = new Command();
        command2.setCommandType(CommandType.GET_TEXT);
        command2.setTarget("strong.big");
        gathererFragment.getCommands().add(command1);
        gathererFragment.getCommands().add(command2);

        return gatherer;
    }
}
