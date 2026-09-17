//TokenService .java(Service Class)
package com.eshwar.service;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.eshwar.constants.PayPalOAuthAPIConstants;
import com.eshwar.dto.PrepareHttpRequest;
import com.eshwar.http.HttpServiceEngine;
import com.eshwar.paypaloauthapi.PayPalOAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService 
{
    
	private final HttpServiceEngine serviceEngine;
	private final ScheduledExecutorService executorService;
	private final ObjectMapper mapper;
	
	private static String accessToken;
	
	@Value("${paypal.oauth.url}")
	private String paypalOauthUrl;
	
	@Value("${paypal.client.id}")
	private String paypalClientId;
	
	@Value("${paypl.secret.id}")
	private String pypalSecretId;
	
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
		
		
	    //calling prepreOauthRequest() method of Token Service Class
		PrepareHttpRequest httpRequest = prepreOauthRequest();
				     
		
		//we have to make the http call to paypal OAuth API Call(to do)
		ResponseEntity<String> jsonResponse = serviceEngine.makeHttpCall(httpRequest);
		
		log.info("Response From HttpServiceEngine Class is , jsonResponse : {} ",jsonResponse);
		
		PayPalOAuthResponse javaObj = null;
		
		try
		{
		   //converting JSON String into Java Object using Jackson 
		    javaObj = mapper.readValue(jsonResponse.getBody(),PayPalOAuthResponse.class);
		    
		    log.info("JSON String into Java Object Conversion , javaObj : {} ",javaObj);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed to Parse From JSON into Java Object...");
			
		}
		
		
	     //what ever we get the access token from the PayPal we are setting to the accessToken instance variable
		 accessToken = javaObj.getAccessToken();
		
		 //calling  scheduleExpireTimeOfAccessToken(---) method of Token Service Class
		 scheduleExpireTimeOfAccessToken(javaObj);
		
		 log.info("New Access Token is Generated and returning the new Access Token, accessToken : {} ",accessToken);
		
		
		return accessToken;
	}

	private void scheduleExpireTimeOfAccessToken(PayPalOAuthResponse javaObj) 
	{
		
		//from paypal returned access token expire time reducing 15 minutes due to the issue like PayPal is saying access token is expired but our system is saying token is valid due to this mismatch we reducing 15 minutes.
		long reduceExpiresInSecs = javaObj.getExpiresIn() - (15 * 60);
		
		
		executorService.schedule(()->
		           
		                     {
		                    	   log.info("Access Token is Expired , Clearing the Token ...");
		                    	   accessToken = null;
		                    	   
			
		                     },reduceExpiresInSecs, java.util.concurrent.TimeUnit.SECONDS);// at particular time(at which time) it will execute(suppose we scheduled for 30 seconds , for every  30 seconds this block will execute).
				
		                     log.info("Access Token Will Expire in "+reduceExpiresInSecs+" Seconds");
	}

	
	private PrepareHttpRequest prepreOauthRequest() 
	{
		//creating custom Headers Class
		HttpHeaders customHeaders=new HttpHeaders();
		customHeaders.setBasicAuth(paypalClientId, pypalSecretId);
		customHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String>multiValueMap=new LinkedMultiValueMap<>();		
		
		multiValueMap.add(PayPalOAuthAPIConstants.GRANT_TYPE,PayPalOAuthAPIConstants.CLIENT_CREDENTIALS);	
		
		//preparing the request and returning the request
		return PrepareHttpRequest.builder()
				                        .httpMethod(HttpMethod.POST)
				                           .url(paypalOauthUrl)
				                             .headers(customHeaders)
				                               .body(multiValueMap)
				                                 .build();
		
	}
}
