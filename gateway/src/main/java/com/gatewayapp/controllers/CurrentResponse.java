package com.gatewayapp.controllers;

public class CurrentResponse {
	private String success;
	private Long timestamp;
	private String base;
	private String date;
	private Object rates;

	public CurrentResponse(String success, Long timestamp, String base, String date, Object rates) {
		this.success = success;
		this.timestamp = timestamp;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public String getSuccess() {
		return success;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getBase() {
		return base;
	}

	public String getDate() {
		return date;
	}

	public Object getRates() {
		return rates;
	}
}
