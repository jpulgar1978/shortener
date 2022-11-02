package com.meli.desafio.domain.usecase.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.desafio.data.entity.Shortener;
import com.meli.desafio.data.repository.ShortenerRepository;

@Service
public class ReadUseCaseImpl implements ReadUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Override
	public String read(String shortUrl) {
		Shortener found = shortenerRepository.findByKey(shortUrl);
		
		return found.getUrl();
	}
	
}