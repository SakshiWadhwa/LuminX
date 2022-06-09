package com.uwindsor.ase.lightpollution.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LightPollutionController {

	public static final Logger logger = LogManager.getLogger(LightPollutionController.class);
}
