package com.tinyurl.service.impl;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class TinyUrlServiceImplTest {
	
	@Mock
	private TinyUrlDao tinyUrlDaoImpl;
	@Mock
	private Base62Helper urlShortner;
	
	private TinyUrlServiceImpl tinyUrlServiceImpl;
	
	@Before
    public void setUp() {
		this.tinyUrlServiceImpl = new TinyUrlServiceImpl();
        Whitebox.setInternalState(this.tinyUrlServiceImpl, "tinyUrlDaoImpl", tinyUrlDaoImpl);
        Whitebox.setInternalState(this.tinyUrlServiceImpl, "urlShortner", urlShortner);
	}

	@Test
	public void testGenerateAndSaveTinyUrl() throws TinyUrlException {
		TinyUrl tinyUrlObj = this.tinyUrlServiceImpl.generateAndSaveTinyUrl("https://www.apple.com/apple-watch-series-1/");
        Mockito.when(this.urlShortner.toBase62(Mockito.any(String.class))).thenReturn(0L);
		assertEquals(tinyUrlObj.getTinyUrl(), "0");
	}
	
	@Test
	public void testGenerateAndSaveTinyUrlNull() throws TinyUrlException {
		TinyUrl tinyUrlObj = this.tinyUrlServiceImpl.generateAndSaveTinyUrl(null);
		assertEquals(tinyUrlObj.getTinyUrl(), "0");
	}
	
	@Test
	public void getLongUrlByTinyUrl() throws TinyUrlException {
		com.tinyurl.h2.model.TinyUrl tinyUrl = new com.tinyurl.h2.model.TinyUrl();
		tinyUrl.setLongUrl("http://www.ritvik.com");
        Mockito.when(this.tinyUrlDaoImpl.getLongUrlByTinyUrl(Mockito.anyLong())).thenReturn(tinyUrl);
		TinyUrl tinyUrlObj = this.tinyUrlServiceImpl.getLongUrlByTinyUrl(123L);
		assertEquals(tinyUrlObj.getLongUrl(), "http://www.ritvik.com");
	}
	
    @Test(expected = RuntimeException.class)
	public void getLongUrlByTinyUrlNullUrl() throws TinyUrlException {
		TinyUrl tinyUrlObj = this.tinyUrlServiceImpl.getLongUrlByTinyUrl(null);
		assertEquals(tinyUrlObj.getLongUrl(), "http://www.ritvik.com");
	}
}
