//TokenService .java(Service Class)
package com.eshwar.service;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.stereotype.Service;
import com.eshwar.http.HttpServiceEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService 
{
    
	private final HttpServiceEngine serviceEngine;
	private final ScheduledExecutorService executorService;
	
	private static String accessToken;
	public String getAccessToken()
	{
		
		
		log.info("TokenService Class getAccessToken() method is Executed...");
		
		//checking wheather the access token contains value or not
		if(accessToken!=null)
		{
			log.info("Access Token is Already Exist, We are Returning Same Access Token , accesToken : {} ",accessToken);
			return accessToken;
		}
		
		log.info("Access Token doesn't exist , generating a new Access Token...");
		
		//we have to make the http call to paypal OAuth API Call(to do)
		String response = serviceEngine.makeHttpCall();
		
		String responseAccessToken="dummy-access-token";
		Integer expiresInSeconds=31668;//in seconds( means nearly 9 hours)
		
		//from above expires time reduce 15 minutes
		//int reduceExpiresInSecs=expiresInSeconds - (15 * 60);
		int reduceExpiresInSecs=30;//for testing purpose
		
		accessToken = responseAccessToken;
		
		executorService.schedule(()->
		           
		                     {
		                    	   log.info("Access Token is Expired , Clearing the Token ...");
		                    	   accessToken = null;
		                    	   
			
		                     },reduceExpiresInSecs, java.util.concurrent.TimeUnit.SECONDS);// at particular time(at which time) it will execute(suppose we scheduled for 30 seconds , for every  30 seconds this block will execute).
				
		        log.info("New Access Token is Generated , accessToken : {} ",accessToken);
		
		
		return accessToken;
	}
}
