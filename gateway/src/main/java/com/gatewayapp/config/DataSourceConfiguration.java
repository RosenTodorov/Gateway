package com.gatewayapp.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class DataSourceConfiguration {
	private static final int RATE_TIME = 100000;

	private String lastData;

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

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSource = DataSourceBuilder.create();
		dataSource.driverClassName(DB_DRIVER);
		dataSource.username(userNameDB);
		dataSource.password(passwordDB);
		dataSource.url(jdbcUrl);
		return dataSource.build();
	}

	@Scheduled(fixedRate = RATE_TIME)
	public void getRates() {
		try {
			DataSource dataSource = this.getDataSource();
			createPreparedStatement(dataSource, lastData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createPreparedStatement(DataSource dataSource, String result) throws SQLException {
		PreparedStatement pstmt = dataSource.getConnection()
				.prepareStatement(insertQuery + "'" + result + "');");
	}

	@Bean(name = "lastData")
	public String getLastData() {
		final String uri = fixerUrl + accessKey;
		RestTemplate restTemplate = new RestTemplate();
		lastData = restTemplate.getForObject(uri, String.class);
		return lastData;
	}
}
