package com.meli.desafio.api.create;

public class CreateResponse {
	private String shortUrl;
	private String message;

	public String getShortUrl() {
		return this.shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}