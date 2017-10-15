package com.tinyurl.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tinyurl.exception.TinyUrlException;
import com.tinyurl.model.TinyUrl;
import com.tinyurl.service.TinyUrlService;

/**
 * 
 * Provides interface maintain tiny urls.
 * 
 *
 */
@Controller
public class TinyUrlWebService {
	
	@Autowired
	private TinyUrlService tinyUrlServiceImpl;
	
    private static Logger log = LoggerFactory.getLogger(TinyUrlWebService.class);


	@RequestMapping("/index")
	public String index() {
		log.info("request for index.jsp");
		return "index";
	}
	
	@RequestMapping(path = "/tinyurl", method = RequestMethod.POST)
	public String generateAndStoreTinyUrl(@RequestParam("longUrl") final String longUrl,final Model model,final HttpServletRequest request) throws MalformedURLException {
		try {
			TinyUrl tinyUrl = tinyUrlServiceImpl.generateAndSaveTinyUrl(longUrl);
		    model.addAttribute("tinyUrl", getBaseUrl(request) +"/"+tinyUrl.getTinyUrl());
		    return "tinyurl";
		}catch(TinyUrlException exception) {
			log.error("Exception ::"+exception.getMessage());
		    model.addAttribute("error", exception.getErrorMessage());
			return "error";
		}
	}
	
	@RequestMapping("/{tinyUrl}")
	public ModelAndView getLongUrl(@PathVariable(value="tinyUrl") Long tinyUrl,final Model model,final HttpServletResponse httpServletResponse) throws MalformedURLException {
		TinyUrl tinyUrlDetails;
		try {
			tinyUrlDetails = tinyUrlServiceImpl.getLongUrlByTinyUrl(tinyUrl);
		    model.addAttribute(tinyUrlDetails.getLongUrl());
		    return new ModelAndView("redirect:" + tinyUrlDetails.getLongUrl());
		} catch (TinyUrlException exception) {
			log.error("Exception ::"+exception.getMessage());
		}catch (Exception exception) {
			log.error("Exception ::"+exception.getMessage());
		}
		return new ModelAndView("error");
	}
	
	private String getBaseUrl(final HttpServletRequest request) throws MalformedURLException {
	    URL requestURL = new URL(request.getRequestURL().toString());
	    String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	    return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	}
}
