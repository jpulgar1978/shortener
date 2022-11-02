package com.meli.desafio.domain.mapper;

import java.util.Date;

import com.meli.desafio.domain.model.Event;

public class EventMapper{
	public static Event createEvent(String type, String status, String payload) {
		Event ev = new Event();
		ev.setDate(new Date());
		ev.setType(type);
		ev.setStatus(status);
		ev.setPayload(payload);
		
		return ev;
	}
}