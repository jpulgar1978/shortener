package com.meli.desafio.data.repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.meli.desafio.data.entity.Shortener;
import com.mongodb.client.result.UpdateResult;

@Repository
public class ShortenerRepositoryImpl implements ShortenerRepository {

	private final String[] listUsers = {"aaa","bbb","ccc"};
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public Shortener findByKey(String key) {
		Query query = new Query();
		query.addCriteria(Criteria.where("key").is(key));
		
		return mongoTemplate.findOne(query, Shortener.class);
	}

	@Override
	public boolean findUser(String user) {
		return Arrays.asList(listUsers).contains(user);
	}

	@Override
	public boolean updateStatus(String key, boolean status) {		
		Query query = new Query();
		query.addCriteria(Criteria.where("key").is(key));
		
		Update update = new Update();
		update.set("status", status);
		
		UpdateResult result = mongoTemplate.updateFirst(query, update, Shortener.class);
		return (result.getModifiedCount()==1);
	}

	@Override
	public boolean updateUrl(String key, String newUrl) {
		Query query = new Query();
		query.addCriteria(Criteria.where("key").is(key));
		
		Update update = new Update();
		update.set("url", newUrl);
		
		UpdateResult result = mongoTemplate.updateFirst(query, update, Shortener.class);
		return (result.getModifiedCount()==1);
	}

	@Override
	public Shortener saveNew(String key, String newUrl) {
		Shortener e = new Shortener();
		e.setKey(key);
		e.setStatus(true);
		e.setUrl(newUrl);
		
		return mongoTemplate.save(e);
	}

	@Override
	public Shortener findByUrl(String url) {
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is(url));
		
		return mongoTemplate.findOne(query, Shortener.class);
	}

}