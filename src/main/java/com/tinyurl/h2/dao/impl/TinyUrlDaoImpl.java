package com.tinyurl.h2.dao.impl;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tinyurl.exception.TinyUrlBusinessException;
import com.tinyurl.h2.dao.TinyUrlDao;
import com.tinyurl.h2.model.TinyUrl;

@Named
public class TinyUrlDaoImpl implements TinyUrlDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createTinyUrl(final TinyUrl tinyUrl) {
		entityManager.persist(tinyUrl);
		entityManager.flush();
	}

	@Override
	public TinyUrl getLongUrlByTinyUrl(final Long tinyUrl) throws TinyUrlBusinessException{
		return entityManager.createQuery("FROM TinyUrl WHERE tinyUrl = :value1", TinyUrl.class)
				.setParameter("value1", tinyUrl).getSingleResult();
	}
}