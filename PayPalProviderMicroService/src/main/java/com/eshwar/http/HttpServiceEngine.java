//HttpServiceEngine.java(This class is responsible for calling the External system APIs (or )  responsible for calling the external calls)
package com.eshwar.http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import com.eshwar.constants.ErrorCodeEnum;
import com.eshwar.dto.PrepareHttpRequest;
import com.eshwar.exception.PayPalProviderException;
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
	   	
		try
		{
			
		
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
		                  
		
		   //returning the Success Response to the Caller
		   return responseEntity;
		}
		
		
		catch (HttpClientErrorException | HttpServerErrorException e)
		{

	        log.error("PayPal API returned an error. status={}, responseBody={} ", e.getStatusCode(), e.getResponseBodyAsString());
	        

		    //if service unavailable (or) Gateway_Time Out then throw this PayPal Provider  exception
		    
	        if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) 
	        {

	            throw new PayPalProviderException(ErrorCodeEnum. EXTERNAL_SERVICE_UNAVAILABLE.getErrorCode()
	            		                         ,ErrorCodeEnum. EXTERNAL_SERVICE_UNAVAILABLE.getErrorMessage()
	                                             , HttpStatus.SERVICE_UNAVAILABLE);
	            
	        }

	        if (e.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) 
	        {

	            throw new PayPalProviderException(ErrorCodeEnum.GATEWAY_TIMEOUT.getErrorCode(),
	                                              ErrorCodeEnum.GATEWAY_TIMEOUT.getErrorMessage(),
	                                              HttpStatus.GATEWAY_TIMEOUT );
	          
	        }
	        
	        
            
	        //returning Valid Error Response to the Caller
	        return ResponseEntity.status(e.getStatusCode())
	        		              .headers(e.getResponseHeaders())
	        		              .body(e.getResponseBodyAsString());
	        
	    }
		
		//For No Response Scenario
		catch (ResourceAccessException e)
		{
		    log.error("Unable to communicate with PayPal external service", e);
		    
		    
		    throw new PayPalProviderException(
		            ErrorCodeEnum.UNKNOWN_EXTERNAL_SERVICE_OUTCOME.getErrorCode(),
		            ErrorCodeEnum.UNKNOWN_EXTERNAL_SERVICE_OUTCOME.getErrorMessage(),
		            HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//For unexpected exceptions
		catch (Exception e)
		{
		    log.error("Unexpected exception occurred while calling PayPal API", e);

		    throw new PayPalProviderException(
		            ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
		            ErrorCodeEnum.GENERIC_ERROR.getErrorMessage(),
		            HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
