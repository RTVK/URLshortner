package com.tinyurl.webservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import com.tinyurl.h2.dao.TinyUrlDao;
import com.tinyurl.service.TinyUrlService;
import com.tinyurl.service.helper.Base62Helper;
import com.tinyurl.service.impl.TinyUrlServiceImpl;

/**
 * 
 * UTs for TinyUrlWebService Controller.
 * 
 *
 */
public class TinyUrlWebServiceTest {
	@Mock
	private TinyUrlService tinyUrlServiceImpl;

	private TinyUrlWebService tinyUrlWebService;

	@Mock
	private TinyUrlDao tinyUrlDaoImpl;

	@Mock
	private Base62Helper urlShortner;

	@Before
	public void setUp() {
		tinyUrlWebService = new TinyUrlWebService();
		tinyUrlServiceImpl = new TinyUrlServiceImpl();
		urlShortner = new Base62Helper();
		Whitebox.setInternalState(this.tinyUrlWebService, "tinyUrlServiceImpl", tinyUrlServiceImpl);
		Whitebox.setInternalState(this.tinyUrlServiceImpl, "tinyUrlDaoImpl", tinyUrlDaoImpl);
		Whitebox.setInternalState(this.tinyUrlServiceImpl, "urlShortner", urlShortner);
	}

	@Test
	public void testIndex() {
		assertEquals(tinyUrlWebService.index(), "index");
	}

	// TODO add more UTs.
}
