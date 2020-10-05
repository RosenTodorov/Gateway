package com.gatewayapp.services;

import com.gatewayapp.entities.RequestData;

public interface IExternalService {

public RequestData save(RequestData statistic);

public boolean requestExists(RequestData statistic);

}