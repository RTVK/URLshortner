package com.tinyurl.service.helper;

import javax.inject.Named;

/**
 * Class to generate base62 values.
 * @author ripandya
 *
 */
@Named
public class Base62Helper {
	
	  /**
	   * Generates base 62 value of a string.
	   *
	   * @return the base 62 value of a string.
	   */
	  public long toBase62(String value) {
	    long result = 0;
	    int power = 1;
	    for (int i = value.length() - 1; i >= 0; i--) {
	      int digit = value.charAt(i) - 48;
	      if (digit > 42) {
	        digit -= 13;
	      } else if (digit > 9) {
	        digit -= 7;
	      }
	      result += digit * power;
	      power *= 62;
	    }
	    return result;
	  }
}