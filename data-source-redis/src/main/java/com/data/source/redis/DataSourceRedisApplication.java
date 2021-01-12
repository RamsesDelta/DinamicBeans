package com.data.source.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.data.source.redis.constans.PropertiesConstans;
import com.data.source.redis.core.BuildRedisDataSource;

@SpringBootApplication
public class DataSourceRedisApplication {
	
	private PropertiesConstans propertiesConstas = new PropertiesConstans();
	/*
	@Autowired
	@Qualifier("session")
	RedisTemplate<String,String> redisTemplate;
	
	private HashOperations hashOperations;*/
	
	public static void main(String[] args) {
		SpringApplication.run(DataSourceRedisApplication.class, args);
	}

	/*@PostConstruct
	public void init(){
		hashOperations = redisTemplate.opsForHash();
		/*hashOperations.put("demoV5", "ejemplo", "prueba");
		System.out.println("insert");
		System.out.println(hashOperations.entries("demoV5"));
	}*/
}
