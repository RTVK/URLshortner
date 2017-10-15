package com.tinyurl.service;


import com.tinyurl.model.TinyUrl;
import com.tinyurl.exception.TinyUrlException;

/**
 * Service class to maintain transactions, call validations,
 * 
 * @author ripandya
 *
 */
public interface TinyUrlService {

	public TinyUrl generateAndSaveTinyUrl(final String longUrl) throws TinyUrlException;
	
	public TinyUrl getLongUrlByTinyUrl(final Long tinyUrl) throws TinyUrlException;
}