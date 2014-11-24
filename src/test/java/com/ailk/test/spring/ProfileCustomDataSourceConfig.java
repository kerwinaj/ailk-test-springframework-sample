package com.ailk.test.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile({ "dev" })
public class ProfileCustomDataSourceConfig extends CustomDataSourceConfig {

	@Override
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		return super.getDataSource();
	}
}
