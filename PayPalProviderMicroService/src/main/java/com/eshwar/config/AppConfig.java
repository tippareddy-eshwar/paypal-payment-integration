// AppConfig .java(Configuration Class
package com.eshwar.config;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		  //creates and returns the RestClient Object.
		  return RestClient.builder().build();
	  }
}
