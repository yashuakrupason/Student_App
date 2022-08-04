package com.studentApp.studentinfo.services;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.studentApp.studentinfo.controller.TokenOrSessionIDResponseDto;
//@EnableKafka
@Service
public class KafkaSubscriber {
	
	public String token;

	//@KafkaListener(topics = "token", groupId="group_id")
	public void listen(TokenOrSessionIDResponseDto res) {
		token = res.getSessionId();
		System.out.println("token in lis :"+ res.getSessionId());
		
	}
	
	public String getTokenFromKafkaListener() {
		return token;
	}
}
