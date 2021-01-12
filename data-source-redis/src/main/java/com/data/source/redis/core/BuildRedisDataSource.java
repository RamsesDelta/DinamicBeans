package com.data.source.redis.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.data.source.redis.config.dto.Conexion;
import com.data.source.redis.constans.PropertiesConstans;


@Configuration
public class BuildRedisDataSource implements EnvironmentAware  {
	
	private static final Logger log = LoggerFactory.getLogger(BuildRedisDataSource.class);
	
	private List<Conexion> dataConexion;
	
	private PropertiesConstans propertiesConstas; 
	
	@Bean
	public Map<String, JedisConnectionFactory> conectionFactory() {
		
		return dataConexion.stream().collect(Collectors.toMap(Conexion::getName , v ->  
	 			new JedisConnectionFactory(new RedisStandaloneConfiguration(v.getHost(), Integer.parseInt(v.getPort()))) 
	 			));	
	}

	@Override
	public void setEnvironment(Environment environment) {
		
		if(environment.getProperty(propertiesConstas.NAME) == null || environment.getProperty(propertiesConstas.NAME).isBlank() ) {
			throw new Error("in propertie datasource.name");
		}
		
		dataConexion = Arrays.asList( environment.getProperty(propertiesConstas.NAME).split(","))
							.stream()
							.map(n -> new Conexion(n, 
									validPropertie(environment.getProperty(String.format(propertiesConstas.HOST, n)),n), 
									validPropertie(environment.getProperty(String.format(propertiesConstas.PORT, n)),n)))
							.collect(Collectors.toList());
	}
	
	static String validPropertie(String propertie, String name) {
		
		if(propertie == null || propertie.isBlank()) {
			throw new  Error("in properties "+name+" "+propertie);
		}
		
		return propertie;
	}
	
}
