package org.constantgatherer.service;

import org.constantgatherer.model.Gatherer;

import java.util.List;

/**
 * Created by ggomes on 25/05/14.
 */
public interface GathererService {
    public Gatherer persist(Gatherer gatherer);
    public Gatherer fetch(Long id);
    public Gatherer fetchByName(String name);
    public List<Gatherer> fetchAll();
    public void run(Gatherer gatherer);
}
