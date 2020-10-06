package com.gatewayapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gatewayapp.entities.RequestData;
import com.gatewayapp.repositories.IRequestDataRepository;

@Component
@Service
public class ExternalService implements IExternalService {
	
	private final IRequestDataRepository requestDataRepository;

	@Autowired
	public ExternalService(IRequestDataRepository requestDataRepository) {
		this.requestDataRepository = requestDataRepository;
	}
	
	@Override
	public RequestData save(RequestData requestData) {
		return this.requestDataRepository.save(requestData);
	}

	@Override
	public boolean requestExists(RequestData requestData) {
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("requestId", match -> match.exact().ignoreCase());
		Example<RequestData> example = Example.of(requestData, matcher);
		
		return requestDataRepository.exists(example);
	}
}
