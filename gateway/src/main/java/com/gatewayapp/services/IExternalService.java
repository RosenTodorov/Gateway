package com.gatewayapp.services;

import com.gatewayapp.entities.RequestData;

public interface IExternalService {

public RequestData save(RequestData data);

public boolean requestExists(RequestData data);

}