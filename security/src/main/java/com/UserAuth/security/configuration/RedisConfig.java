package com.UserAuth.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisConfig {
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("localhost");
		config.setPort(6379);
		
		return new LettuceConnectionFactory(config);
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		final RedisTemplate<String, Object> redistemplate = new RedisTemplate<>();
		redistemplate.setConnectionFactory(redisConnectionFactory());
		return redistemplate;
	}
}
