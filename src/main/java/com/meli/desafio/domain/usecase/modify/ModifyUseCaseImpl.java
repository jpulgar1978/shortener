package com.meli.desafio.domain.usecase.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.desafio.config.KafkaMessageProducer;
import com.meli.desafio.data.repository.ShortenerRepository;
import com.meli.desafio.domain.mapper.EventMapper;
import com.meli.desafio.domain.model.Event;

@Service
public class ModifyUseCaseImpl implements ModifyUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Autowired
	private KafkaMessageProducer<Event> kafkaMessageProducer;
	
	@Override
	public boolean setStatus(String shortUrl, String user, boolean status) {
		if (!shortenerRepository.findUser(user)) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "USER DOES NOT EXISTS", shortUrl));
			return false;
		}
		boolean result = shortenerRepository.updateStatus(shortUrl, status);
		if (result) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "STATUS OK", shortUrl));
		}else {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "STATUS ERROR", shortUrl));
		}
		return result;
	}

	@Override
	public boolean setNewUrl(String shortUrl, String user, String newUrl) {
		if (!shortenerRepository.findUser(user)) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "USER DOES NOT EXISTS", shortUrl));
			return false;
		}
		boolean result = shortenerRepository.updateUrl(shortUrl, newUrl);
		if (result) {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "URL OK", shortUrl));
		}else {
			kafkaMessageProducer.sendMessage(EventMapper.createEvent("modify", "URL ERROR", shortUrl));
		}
		return result;
	}
	
}