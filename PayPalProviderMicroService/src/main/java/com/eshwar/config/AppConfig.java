// AppConfig .java(Configuration Class
package com.eshwar.config;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig 
{

	  @Bean
	  public ScheduledExecutorService scheduledExecutorService()
	  {
		  log.info(" AppConfig Class scheduledExecutorService() method is Executed...");
		  
		  //Executors.newSingleThreadScheduledExecutor() : It creates a ScheduledExecutorService with one worker thread. And That thread can execute scheduled tasks.
		  return Executors.newSingleThreadScheduledExecutor();
	  }
	  
	 @Bean
	 public RestClient restClient()
	  {
		  log.info(" AppConfig Class restClient() method is Executed...");
		  
		  //PoolingHttpClientConnectionManager is a class who manages a pool of HTTP connections.Instead of creating a new TCP connection for every PayPal request:
          //Request 1 → new connection → PayPal , Request 2 → new connection → PayPal , Request 3 → new connection → PayPal.The pool can reuse connections for the incoming requests.
	        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
	                        .setMaxConnTotal(100)//The connection pool can have a maximum of 100 connections in total.You cannot have more than 100 pooled connections at the same time.
	                        .setMaxConnPerRoute(20)//A route essentially represents a connection destination.For your application, if you're mainly calling https://api-m.sandbox.paypal.com
	                                                //then that PayPal server is one route. You allow a maximum of 20 connections to the same route.
	                       
	                        .setConnectionTimeToLive(TimeValue.ofMinutes(5))//It gives a connection a maximum lifetime of 5 minutes.After 5 minutes this connection is unused.
	                        .build();//This actually creates the PoolingHttpClientConnectionManager Class Object with the configuration we specified above.

	        //creating the actual Apache HttpClient 5 
	        CloseableHttpClient httpClient =HttpClients.custom()
	                        .setConnectionManager(connectionManager)//This tells to the  Apache HttpClient that "Use this connection manager for managing HTTP connections."
	                        .evictExpiredConnections()//This tells to the  HttpClient that remove the expired connections from the Pool.
	                        .evictIdleConnections(TimeValue.ofMinutes(1))//It says that idle connections can be evicted(removed from the Pool) after 1 minute of inactivity.
	                        .build();//This actually creates the HttpClient 5  Class Object

	        //This is the bridge between Spring RestClient and Apache HttpClient. Without this, Spring's RestClient doesn't know that it should use your customized Apache HttpClient.
	        HttpComponentsClientHttpRequestFactory factory =new HttpComponentsClientHttpRequestFactory(httpClient);

	        //This creates the Spring RestClient and tells it that "Use this request factory."
	        return RestClient.builder() 
	        		.requestFactory(factory)
	                .build();
		 
	  }
}
