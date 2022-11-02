package com.meli.desafio.domain.usecase.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.desafio.config.KafkaMessageProducer;
import com.meli.desafio.data.entity.Shortener;
import com.meli.desafio.data.repository.ShortenerRepository;
import com.meli.desafio.domain.mapper.EventMapper;
import com.meli.desafio.domain.model.Event;

@Service
public class ReadUseCaseImpl implements ReadUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Autowired
	private KafkaMessageProducer<Event> kafkaMessageProducer;
	
	@Override
	public String read(String shortUrl) {
		Shortener found = shortenerRepository.findByKey(shortUrl);
		
		if (found == null) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("read", "NOT FOUND", shortUrl));
			return "";
		}
		if (!found.getStatus()) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("read", "NOT ENABLED", found.getKey()));
			return "";
		}
		
		kafkaMessageProducer.sendMessage(EventMapper.createEvent("read", "OK", found.getUrl()));
		return found.getUrl();
	}
	
}