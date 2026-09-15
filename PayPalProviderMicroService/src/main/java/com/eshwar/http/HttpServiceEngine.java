//HttpServiceEngine.java(This class is responsible for calling the External system APIs (or )  responsible for calling the external calls)
package com.eshwar.http;
import java.util.function.Consumer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpServiceEngine 
{

	private final RestClient restClient;
	
	public String makeHttpCall()
	{
		log.info(" HttpServiceEngine Class makeHttpCall() method is Executed...");
		log.info("Rest Client Object , restClient : {} ",restClient);
	   
		//calling the PayPal OAuth API to get the Access Token
		
		//creating custom Headers Class
		HttpHeaders customHeaders=new HttpHeaders();
		customHeaders.setBasicAuth("AUl1CtucJCKoT6dtdJ-r7UU2UpUrPeTL8N1mGPB11IXV9H_BT9g0Y9wTG_Ul9qT_pildpUSka7Jv_0Os","EHjAb8eWWLgHQhFSQKzohATE3gk92vCgqXpb3jNyhAPWItVDD81BSeQNgjrBjE4xEj4hHYwy19d3769L");
		customHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String>multiValueMap=new LinkedMultiValueMap<>();		
		multiValueMap.add("grant_type","client_credentials");
		Consumer<HttpHeaders>headers=new Consumer<HttpHeaders>()
				{

					@Override
					public void accept(HttpHeaders restClientHttpHeaders) 
					{
						
						log.info("Consumer interface accept(---) method is Executed...");
						//we are setted the values which is there in customHeaders, when this method execute , rest client knows the actual values
						restClientHttpHeaders.addAll(customHeaders);
						
					}
			
				};
		
		
		ResponseEntity<String> responseEntity = restClient.method(HttpMethod.POST)
		            .uri("https://api-m.sandbox.paypal.com/v1/oauth2/token")
		               //when we write .headers(headers) it will be called automatically by the rest client,  because the RestClient Libary knows their own Rest Client  header only but not our header
		                .headers(headers) 
		                  .body(multiValueMap)
		                  
		                  //before retrieve () is all about preparing the request and from the retrieve() is all about calling the API Call and receiving the response
		                    .retrieve()
		                      //if we want body , headers, Http Status code then use toEntity(--) it will return ResponseEntity<>.
		                         .toEntity(String.class);
		                
		        log.info("Response Entity , responseEntity : {} ",responseEntity) ;      
		                  
		
		return responseEntity.getBody();
	}
}
