package org.constantgatherer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Class.
 * User: ggomes
 */
@Controller
public class DocsController {

    @RequestMapping("/apidocs")
    public String render(){
        return "index";
    }
}
