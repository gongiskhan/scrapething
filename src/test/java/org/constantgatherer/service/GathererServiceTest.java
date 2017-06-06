package org.constantgatherer.service;

import org.constantgatherer.config.PersistenceConfig;
import org.constantgatherer.config.ServiceConfig;
import org.constantgatherer.config.SpringConfig;
import org.constantgatherer.model.Gatherer;
import static org.junit.Assert.*;

import org.constantgatherer.model.GathererFragment;
import org.constantgatherer.model.GathererType;
import org.constantgatherer.model.webdriver.Command;
import org.constantgatherer.model.webdriver.CommandType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ggomes on 25/05/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class GathererServiceTest {

    @Autowired
    GathererService gathererService;
    Gatherer gatherer;

    @Before
    public void setUp(){
        gatherer = new Gatherer();
        gatherer.setName("gath1");
        gatherer.setType(GathererType.SCRAPER);
    }

    @Test
    @Rollback(true)
    public void testRun(){

        GathererFragment frag = new GathererFragment();
        frag.getCommands().add(new Command(CommandType.OPEN,"http://www.eatmatic.info/index.html"));
        frag.getCommands().add(new Command(CommandType.GET_TEXT,"#logoTitle"));
        gatherer.getFragments().add(frag);
        gathererService.run(gatherer);

        assertTrue(true);
    }


    /*
    @Test
    @Rollback(true)
    public void testPersist(){
        gathererService.persist(gatherer);
        Gatherer gatherer1 = gathererService.fetchByName("gath1");
        assertNotNull(gatherer1);
        assertEquals(GathererType.SCRAPER,gatherer1.getType());
    }

    @Test(expected = JpaSystemException.class)
    @Rollback(true)
    public void testPersist_duplicate(){
        gathererService.persist(gatherer);
        Gatherer gatherer1 = new Gatherer();
        gatherer1.setName("gath1");
        gathererService.persist(gatherer1);
    }
    */
}
