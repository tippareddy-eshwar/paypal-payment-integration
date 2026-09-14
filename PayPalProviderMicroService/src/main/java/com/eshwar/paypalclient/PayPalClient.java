//PayPalClient .java 
package com.eshwar.paypalclient;
import org.springframework.stereotype.Component;
import com.eshwar.http.HttpServiceEngine;
import com.eshwar.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PayPalClient 
{
	
	private final TokenService tokenService;
	private final HttpServiceEngine serviceEngine;
 
	public String makeCall()
	{
		log.info("PayPalClient Class makeCall() method is Executed...");
		
		//get the Access Token for that calling the getAccessToken() method of TokenService Class
		String result = tokenService.getAccessToken();
		
		log.info("Response From the getAccessToken() method of Token Service Class is : "+result);
		//make Http call using HttpServiceEngine Class
		
		String httpResponse = serviceEngine.makeHttpCall();
		log.info("Response From the makeHttpCall() method of HttpServiceEngine Class is : "+httpResponse);
		return httpResponse;
				
	}
	  
}
