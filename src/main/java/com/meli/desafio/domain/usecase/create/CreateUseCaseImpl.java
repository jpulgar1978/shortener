package com.meli.desafio.domain.usecase.create;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.meli.desafio.data.entity.Shortener;
import com.meli.desafio.data.repository.ShortenerRepository;

@Service
public class CreateUseCaseImpl implements CreateUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Override
	public String create(String url) {
		Shortener foundUrl = shortenerRepository.findByUrl(url);
		if (foundUrl!=null) {
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
		return s.getKey();
	}
	
}