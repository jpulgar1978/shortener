package com.meli.desafio.domain.usecase.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.desafio.data.repository.ShortenerRepository;

@Service
public class ModifyUseCaseImpl implements ModifyUseCase {

	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Override
	public boolean setStatus(String shortUrl, String user, boolean status) {
		if (!shortenerRepository.findUser(user)) {
			return false;
		}
		return shortenerRepository.updateStatus(shortUrl, status);
	}

	@Override
	public boolean setNewUrl(String shortUrl, String user, String newUrl) {
		if (!shortenerRepository.findUser(user)) {
			return false;
		}
		return shortenerRepository.updateUrl(shortUrl, newUrl);
	}
	
}