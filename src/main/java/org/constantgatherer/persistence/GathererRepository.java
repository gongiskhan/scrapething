package org.constantgatherer.persistence;

import org.constantgatherer.model.Gatherer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ggomes on 25/05/14.
 */
public interface GathererRepository extends CrudRepository<Gatherer, Long> {
    public Gatherer findByName(String name);
}
