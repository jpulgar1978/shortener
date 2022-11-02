package com.meli.desafio.data.repository;

import com.meli.desafio.data.entity.Shortener;

public interface ShortenerRepository {
	public Shortener findByKey(String key);
	public Shortener findByUrl(String url);
	public Shortener saveNew(String key, String newUrl);
	public boolean findUser(String user);
	public boolean updateStatus(String key, boolean status);
	public boolean updateUrl(String key, String newUrl);
}