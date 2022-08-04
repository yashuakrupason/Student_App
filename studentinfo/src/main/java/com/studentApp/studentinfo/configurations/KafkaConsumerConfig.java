package com.studentApp.studentinfo.configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.studentApp.studentinfo.controller.TokenOrSessionIDResponseDto;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, TokenOrSessionIDResponseDto> consumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		
		return new DefaultKafkaConsumerFactory<String, TokenOrSessionIDResponseDto>(config, new StringDeserializer(),
				new JsonDeserializer<>(TokenOrSessionIDResponseDto.class,false));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,TokenOrSessionIDResponseDto> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String,TokenOrSessionIDResponseDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	
	return factory;
	}
}
