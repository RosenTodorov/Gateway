package com.gatewayapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gatewayapp.entities.RequestData;
import com.gatewayapp.services.IExternalService;
import com.google.gson.Gson;

@Validated
@Component
@RestController
public class ServiceController {
	private static final String serviceName = "EXT_SERVICE_1";

	private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	private final IExternalService externalService;

	@Autowired
	public String lastData;

	@Autowired
	public ServiceController(IExternalService externalService) {
		this.externalService = externalService;
	}

	@PostMapping(value = "/json_api/current", consumes = "application/json", produces = "application/json")
	public CurrentResponse triggerLastCurrencyData(@RequestBody CurrentRequest request) {
		RequestData data = externalService.addRequestData(serviceName, request.getRequestId(), request.getTimestamp(),
				request.getClient());
		if (externalService.requestExists(data)) {
			logger.warn("Request {} exists!", data.toString());
		 // return system error
		}
		RequestData saved = externalService.save(data);
		logger.info("RequestData {} added!", saved.toString());

		CurrentResponse currentResponse = new Gson().fromJson(lastData, CurrentResponse.class);
		return currentResponse;
	}
}
