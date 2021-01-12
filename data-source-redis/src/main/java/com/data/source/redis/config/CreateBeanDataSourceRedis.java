package com.data.source.redis.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.data.source.redis.core.BuildRedisDataSource;


public class CreateBeanDataSourceRedis implements BeanFactoryPostProcessor {

	private static final Logger log = LoggerFactory.getLogger(CreateBeanDataSourceRedis.class);
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
		Map<String, BuildRedisDataSource> factories = beanFactory.getBeansOfType(BuildRedisDataSource.class);
		registerBeans(registry, factories);
	}

	private void registerBeans(BeanDefinitionRegistry registry, Map<String, BuildRedisDataSource> factories) {
				
		factories.forEach((k,v) -> {
			
			v.conectionFactory().forEach((name, fatory) ->{
				
				GenericBeanDefinition bd = new GenericBeanDefinition();
				
				bd.setBeanClass(RedisTemplate.class);
								
				bd.getPropertyValues().add("connectionFactory", fatory);
				
				registry.registerBeanDefinition(name, bd);
			});
			
		});
	}
}
