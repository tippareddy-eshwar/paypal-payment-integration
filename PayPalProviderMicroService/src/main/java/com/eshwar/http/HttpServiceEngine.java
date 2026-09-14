//HttpServiceEngine.java
package com.eshwar.http;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpServiceEngine 
{

	public String makeHttpCall()
	{
		log.info(" HttpServiceEngine Class makeHttpCall() method is Executed...");
		
		return "This response is from the makeHttpCall() method of  HttpServiceEngine...";
	}
}
