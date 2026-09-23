//PayPalClient .java 
package com.eshwar.paypalclient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eshwar.dto.PrepareHttpRequest;
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
 
	public ResponseEntity<String> makeCall(PrepareHttpRequest paypalCreateOrderHttpRequest)
	{
		
		log.info("PayPalClient Class makeCall() method is Executed, paypalCreateOrderHttpRequest : {} ",paypalCreateOrderHttpRequest);
		
		//get the Access Token for that calling the getAccessToken() method of TokenService Class
		String accessToken = tokenService.getAccessToken();
		
		log.info("Response From the getAccessToken() method of Token Service Class is : "+ accessToken);
		
	
		HttpHeaders headers = paypalCreateOrderHttpRequest.getHeaders();
		if(headers==null)
		{
			headers = new HttpHeaders();
			paypalCreateOrderHttpRequest.setHeaders(headers);
		}
		
		//get the access token and set it to the header as Barer Token
		headers.setBearerAuth(accessToken);
		
		//make Http call using HttpServiceEngine Class
		ResponseEntity<String> httpResponse = serviceEngine.makeHttpCall(paypalCreateOrderHttpRequest);
		log.info("Response From the makeHttpCall() method of HttpServiceEngine Class is, httpResponse : {} ", httpResponse);
		return httpResponse;
				
	}
	  
}
