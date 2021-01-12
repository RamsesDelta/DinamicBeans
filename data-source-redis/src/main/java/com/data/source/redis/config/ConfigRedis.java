package com.data.source.redis.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import com.data.source.redis.core.BuildRedisDataSource;

@Configuration
public class ConfigRedis {
	
	@Bean
	CreateBeanDataSourceRedis configBeansRedis() {
		return new CreateBeanDataSourceRedis();
	}
	

}
