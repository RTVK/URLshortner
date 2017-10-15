package com.tinyurl.h2.dao;

import com.tinyurl.exception.TinyUrlBusinessException;
import com.tinyurl.h2.model.TinyUrl;

/**
 * Data access layer for tinyurl details.
 * @author ripandya
 *
 */
public interface TinyUrlDao {

	public void createTinyUrl(final TinyUrl tinyUrl);
	public TinyUrl getLongUrlByTinyUrl(final Long tinyUrl)  throws TinyUrlBusinessException;
}