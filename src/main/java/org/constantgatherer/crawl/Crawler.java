package org.constantgatherer.crawl;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 14:09
 * Copyright Tango Telecom 2014
 */
public interface Crawler extends Runnable{
    public void gatherData();
    public void publishData();
}
