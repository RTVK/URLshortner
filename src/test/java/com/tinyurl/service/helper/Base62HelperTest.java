package com.tinyurl.service.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Base62Helper.
 * @author ripandya
 *
 */
public class Base62HelperTest {
	
    private Base62Helper base62Helper = null;
    
    @Before
    public void setUp() {
    		base62Helper = new Base62Helper();
    }

    @Test
    public void testToBase62() {
    		assertEquals(base62Helper.toBase62("https://www.apple.com/apple-watch-series-1/"), 1302057409);
    }
    
    @Test(expected = RuntimeException.class)
    public void testToBase62NullUrl() {
    		assertEquals(base62Helper.toBase62(null), 1302057409);
    }
}