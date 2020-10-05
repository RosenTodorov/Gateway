package com.gatewayapp.gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

@Component
public class EventShedule {
	
	@Value("${access.key}")
	private String accessKey;
    
	@Value("${spring.datasource.driver}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	
	@Value("${spring.datasource.username}")
	private String userNameDB;
	
	@Value("${spring.datasource.password}")
	private String passwordDB;
	
	@Value("${fixer.io.url}")
	private String fixerUrl;
	
	@Value("${insert.query}")
	private String insertQuery;
	
	@Scheduled(fixedRate=100000) 
	public void getRates(){
	    final String uri = fixerUrl + accessKey;
	    
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    Connection connection;
		try {
			connection = getConnection();
			PreparedStatement query = createPreparedStatement(connection, result);
			writeLastCurrencyData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return DriverManager.getConnection(jdbcUrl, userNameDB, passwordDB);
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String result) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(insertQuery + "'" + result + "') RETURNING* ;");
		return pstmt;
	}
	
	private void writeLastCurrencyData(PreparedStatement query) throws SQLException {
		try (ResultSet rsVolumeID = query.executeQuery()) {
			while (rsVolumeID.next()) {
				String success = rsVolumeID.getString(1);
				int timestamp = rsVolumeID.getInt(2);
				String base = rsVolumeID.getString(3);
				String date = rsVolumeID.getString(4);
				Object rates = rsVolumeID.getObject(5);
				// add the last data and return it in @PostMapping("/current")
			}
		}
	}
}

