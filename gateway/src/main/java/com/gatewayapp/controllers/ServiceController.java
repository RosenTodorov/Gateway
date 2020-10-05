package com.gatewayapp.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gatewayapp.entities.RequestData;
import com.gatewayapp.services.IExternalService;

@Validated
@RestController
@RequestMapping("/json_api")
public class ServiceController {
	
	private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	private final IExternalService externalService;
	
	@Autowired
	public ServiceController(IExternalService externalService) {
		this.externalService = externalService;
	}
	
	@PostMapping("/current")
	@RequestMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<RequestData> addData(@Valid @RequestBody RequestData data) {
		if (externalService.requestExists(data)) {
			logger.warn("Request {} exists!", data.toString());
			return new ResponseEntity<RequestData>(HttpStatus.CONFLICT);
		}
		RequestData saved = externalService.save(data);
		
     // return the values from writeLastCurrencyData(); or make select query for last data from rates table
		
     	logger.info("Data {} added!", saved.toString());

		return new ResponseEntity<RequestData>(saved, HttpStatus.CREATED);
	}
}

