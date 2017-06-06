package org.constantgatherer.service.impl;

import org.constantgatherer.crawl.CrawlManager;
import org.constantgatherer.model.Gatherer;
import org.constantgatherer.model.GathererFragment;
import org.constantgatherer.persistence.GathererRepository;
import org.constantgatherer.service.GathererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ggomes on 25/05/14.
 */
@Service
public class GathererServiceImpl implements GathererService{

    @Autowired
    private GathererRepository gathererRepository;

    @Autowired
    private CrawlManager crawlManager;

    @Override
    public Gatherer persist(Gatherer gatherer) {

        return gathererRepository.save(gatherer);
    }

    @Override
    public Gatherer fetch(Long id) {
        return gathererRepository.findOne(id);
    }

    @Override
    public Gatherer fetchByName(String name) {
        return gathererRepository.findByName(name);
    }

    @Override
    public List<Gatherer> fetchAll() {
        return (List<Gatherer>) gathererRepository.findAll();
    }

    @Override
    public void run(Gatherer gatherer) {
        crawlManager.clean();
        crawlManager.registerCrawler(gatherer);
        crawlManager.startCrawlers();
    }
}
