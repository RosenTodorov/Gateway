package com.gatewayapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "requestdata")
public class RequestData implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column(nullable = false)
	@NotEmpty
	private String serviceName;

	@Column(unique = true, nullable = false)
	@NotEmpty
	private String requestId;

	@Column(nullable = false)
	private Long timestamp;

	@Column(unique = true, nullable = false)
	private Long client;

	public RequestData() {
	}

	public RequestData(String serviceName, String requestId, Long timestamp, Long client) {
		super();
		this.serviceName = serviceName;
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.client = client;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getTime() {
		return timestamp;
	}

	public void setTime(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}
}
