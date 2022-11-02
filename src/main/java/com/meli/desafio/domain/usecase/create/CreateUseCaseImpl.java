package com.meli.desafio.domain.usecase.create;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.meli.desafio.config.KafkaMessageProducer;
import com.meli.desafio.data.entity.Shortener;
import com.meli.desafio.data.repository.ShortenerRepository;
import com.meli.desafio.domain.mapper.EventMapper;
import com.meli.desafio.domain.model.Event;

@Service
public class CreateUseCaseImpl implements CreateUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Autowired
	private KafkaMessageProducer<Event> kafkaMessageProducer;
	
	@Override
	public String create(String url) {
		Shortener foundUrl = shortenerRepository.findByUrl(url);
		if (foundUrl!=null) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("create", "RELOAD OK", foundUrl.getKey()));
			return foundUrl.getKey();
		}
		String hash = Hashing.sha256().hashString(url, StandardCharsets.UTF_8).toString();
		boolean done = false;
		int i = 0;
		while (!done) {
			String hashkey = hash.substring(i, i + 7);
			Shortener foundKey = shortenerRepository.findByKey(hashkey);
			
			if (foundKey!=null) {
				i = i + 1;
			}else{
				done = true;
			}
		}
		
		Shortener s = shortenerRepository.saveNew(hash.substring(i, i + 7), url);
		kafkaMessageProducer.sendMessage(EventMapper.createEvent("create", "NEW OK", s.getKey()));
		return s.getKey();
	}
	
}