package com.meli.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaMessageProducer <T> {
	
    @Value(value = "${kafka.topicName}")
    private String topicName;
    
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    
    @Autowired
    public KafkaTemplate<String, Object> kafkaMessageTemplate;
    
	public void sendMessage(T message) {
	    ListenableFuture<SendResult<String, Object>> future = kafkaMessageTemplate.send(topicName, message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
	        @Override
	        public void onSuccess(SendResult<String, Object> result) {
	            System.out.println("Sent message=[" + message + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message=[" 
	              + message + "] due to : " + ex.getMessage());
	        }
	    });
	}
}