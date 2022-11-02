package com.meli.desafio.domain.usecase.modify;

public interface ModifyUseCase {
	public boolean setStatus(String shortUrl, String user, boolean status);
	public boolean setNewUrl(String shortUrl, String user, String newUrl);
}