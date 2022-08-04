package com.UserAuth.security.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class SessionRegService {
	
	private final ValueOperations<String, String> redisSessionStorage;
	
	@Autowired
	SessionRegRepository sessionRepo;
	
	@Autowired
	public SessionRegService(final RedisTemplate<String, String> redisTemplate) {
		redisSessionStorage = redisTemplate.opsForValue();
;		
	}
	
	public String registerSession(String username) {
		if(username == null) {
			throw new RuntimeException("User needs to be provided");
		}
		
		final String sessionId = new String(Base64.getEncoder().encode(
				UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
		
		try {
			redisSessionStorage.set(sessionId, username);
			return sessionId;
		}
		catch(final Exception ex){
			ex.printStackTrace();
			return sessionRepo.save(new SessionRegistry(username,sessionId)).getSessionId();

		}
		
		
	}
	
	public String getUsernameForSessionId(final String sessionId) {

		try {
			return redisSessionStorage.get(sessionId);
		}
		catch(final Exception ex){
			ex.printStackTrace();
			return sessionRepo.findById(sessionId).get().getUsername();

		}
	}
}
