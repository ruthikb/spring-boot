package com.xworkz.ecomerce.restController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultController {
    
    Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping("")
    public  String defaultController(){
        logger.info("Running default controller");

        return "Welcome to Ecomerce Application";
    }


}
