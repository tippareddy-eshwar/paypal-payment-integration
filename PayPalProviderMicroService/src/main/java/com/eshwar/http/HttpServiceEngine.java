//HttpServiceEngine.java(This class is responsible for calling the External system APIs (or )  responsible for calling the external calls)
package com.eshwar.http;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.eshwar.dto.PrepareHttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpServiceEngine 
{

	private final RestClient restClient;
	
	public ResponseEntity<String> makeHttpCall(PrepareHttpRequest httpRequest)
	{
		log.info("HttpServiceEngine Class makeHttpCall() method is Executed , httpRequest : {} ",httpRequest);
		log.info("Rest Client Object , restClient : {} ",restClient);
	   
		//calling the PayPal OAuth API to get the Access Token
		
		ResponseEntity<String> responseEntity= restClient.method(httpRequest.getHttpMethod())
		            .uri(httpRequest.getUrl())
		               //when we write .headers(headers) it will be called automatically by the rest client,  because the RestClient Libary knows their own Rest Client  header only but not our header
		                .headers(restClientHttpHeaders  ->   restClientHttpHeaders.addAll(httpRequest.getHeaders())) 
		                  .body(httpRequest.getBody())
		                  
		                     //before retrieve () is all about preparing the request and from the retrieve() is all about calling the API Call and receiving the response
		                     .retrieve()
		                        //if we want body , headers, Http Status code then use toEntity(--) it will return ResponseEntity<>.
		                         .toEntity(String.class);
		                
		        log.info("Response Entity , responseEntity : {} ",responseEntity) ;      
		                  
		
		return responseEntity;
	}
}
