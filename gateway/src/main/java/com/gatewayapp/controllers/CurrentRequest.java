package com.gatewayapp.controllers;

public class CurrentRequest {
	private String requestId;
	private Long timestamp;
	private Long client;
	private String currency;

	public CurrentRequest() {

	}

	public CurrentRequest(String requestId, Long timestamp, Long client, String currency) {
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.client = client;
		this.currency = currency;
	}

	public String getRequestId() {
		return requestId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Long getClient() {
		return client;
	}

	public String getCurrency() {
		return currency;
	}
}
