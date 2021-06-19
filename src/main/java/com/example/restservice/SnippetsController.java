package com.example.restservice;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnippetsController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	HashMap<String, String> urlSnippetMap = new HashMap<String, String>();
	HashMap<String, Timestamp> snippetExpiryMap = new HashMap<String, Timestamp>();


	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	

	@PostMapping(value = "/snippets", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OutputSnippet loadSnippet(@RequestBody InputSnippet inputSnippet) {
		System.out.println(inputSnippet.toString());
		OutputSnippet outputSnippet = new OutputSnippet();
		outputSnippet.name = inputSnippet.name;

		outputSnippet.snippet = inputSnippet.snippet;
		
		String url = "http://localhost:8080/snippets/" + inputSnippet.name;
		urlSnippetMap.put(url, inputSnippet.snippet);
		outputSnippet.setUrl(url);
		
		Timestamp currentTS = new Timestamp(System.currentTimeMillis());
		int sec = 30;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTS.getTime());
        cal.add(Calendar.SECOND, sec);
        Timestamp expTimeStamp = new Timestamp(cal.getTime().getTime());
		snippetExpiryMap.put(url, expTimeStamp);
		outputSnippet.setExpires_at(expTimeStamp.toString());
		return outputSnippet;

	}


	@GetMapping(value = "/snippets/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OutputSnippet getSnippet(@RequestParam String name) {
		System.out.println(name);
		OutputSnippet outputSnippet = new OutputSnippet();
		String url = "http://localhost:8080/snippets/" + name;
		Timestamp expTimeStamp = snippetExpiryMap.get(url);
		Timestamp currentTS = new Timestamp(System.currentTimeMillis());
		if (currentTS.after(expTimeStamp)){
			throw new RequestExpiredException();
		}
		else {
			int sec = 30;
	        Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(currentTS.getTime());
	        cal.add(Calendar.SECOND, sec);
	        Timestamp newExpTimeStamp = new Timestamp(cal.getTime().getTime());
			snippetExpiryMap.put(url, newExpTimeStamp);
				
		}
		
		outputSnippet.setUrl(url);
		outputSnippet.name = name;
		outputSnippet.expires_at = "";
		outputSnippet.snippet = urlSnippetMap.get(url);
		
		return outputSnippet;
	}
}

