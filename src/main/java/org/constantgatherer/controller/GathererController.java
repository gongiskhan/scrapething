package org.constantgatherer.controller;

import org.constantgatherer.error.GathererException;
import org.constantgatherer.model.Gatherer;
import org.constantgatherer.service.GathererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * Created by ggomes on 25/05/14.
 */
@RestController
@RequestMapping(value = "/gatherer", produces = MediaType.APPLICATION_JSON_VALUE)
public class GathererController {

    @Autowired
    GathererService gathererService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Gatherer>> list(){
        return new ResponseEntity<>(gathererService.fetchAll(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Gatherer> save(@RequestBody Gatherer gatherer) throws GathererException, UnsupportedEncodingException, GeneralSecurityException {
        return new ResponseEntity<Gatherer>(gathererService.persist(gatherer), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/run")
    public @ResponseBody
    ResponseEntity<Gatherer> run(@RequestBody Gatherer gatherer) throws GathererException, UnsupportedEncodingException, GeneralSecurityException {
        gathererService.run(gatherer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
