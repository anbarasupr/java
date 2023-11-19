package com.demo.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean(name = "jedisConnectionFactory")
	JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(6379);
		factory.setUsePool(true);
		return factory;
	}

	// Creating Connection with Redis
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//		return new LettuceConnectionFactory();
//	}

	// Creating RedisTemplate for Entity 'Employee'
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Employee> redisTemplate() {
		RedisTemplate<String, Employee> empTemplate = new RedisTemplate<>();
		empTemplate.setConnectionFactory(redisConnectionFactory());
		return empTemplate;
	}
}