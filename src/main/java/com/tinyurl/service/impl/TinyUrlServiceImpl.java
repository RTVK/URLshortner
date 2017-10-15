package com.tinyurl.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.tinyurl.common.ErrorCodes;
import com.tinyurl.exception.TinyUrlException;
import com.tinyurl.h2.dao.TinyUrlDao;
import com.tinyurl.model.TinyUrl;
import com.tinyurl.service.TinyUrlService;
import com.tinyurl.service.helper.Base62Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@CacheConfig(cacheNames = "tinyurlcache")
@Transactional
public class TinyUrlServiceImpl implements TinyUrlService {

	@Autowired
	private TinyUrlDao tinyUrlDaoImpl;
	
	@Autowired
	private Base62Helper urlShortner;
	
    private static Logger log = LoggerFactory.getLogger(TinyUrlServiceImpl.class);
	

	@Override
	@Cacheable("tinyurlcache")
	public TinyUrl generateAndSaveTinyUrl(final String longUrl) throws TinyUrlException {
		log.info(">>>didn't find in cache");
		long tinyUrl = this.urlShortner.toBase62(longUrl);
		com.tinyurl.h2.model.TinyUrl tinyUrlToPersist = new com.tinyurl.h2.model.TinyUrl();
		tinyUrlToPersist.setLongUrl(longUrl);
		tinyUrlToPersist.setTinyUrl(Long.parseLong(""+Math.abs(tinyUrl)));
		tinyUrlDaoImpl.createTinyUrl(tinyUrlToPersist);
		TinyUrl tinyUrlObj = new TinyUrl();
		try {
			BeanUtils.copyProperties(tinyUrlObj, tinyUrlToPersist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TinyUrlException(ErrorCodes.GENERIC_SYS_EXCEPTION, e.getMessage());
		}
		return tinyUrlObj;
	}

	@Override
	@Cacheable
	public TinyUrl getLongUrlByTinyUrl(Long tinyUrl) throws TinyUrlException {
		log.info(">>>didn't find in cache");
		TinyUrl tinyUrlObj = new TinyUrl();
		com.tinyurl.h2.model.TinyUrl tinyUrlFromDB = tinyUrlDaoImpl.getLongUrlByTinyUrl(tinyUrl);
		try {
			BeanUtils.copyProperties(tinyUrlObj, tinyUrlFromDB);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TinyUrlException(ErrorCodes.GENERIC_SYS_EXCEPTION, e.getMessage());
		}
		return tinyUrlObj;
	}


}
