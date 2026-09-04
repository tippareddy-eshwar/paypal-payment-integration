// AppConfig .java(Configuration Class)
package com.eshwar.config;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig 
{
	 @Bean
     public ModelMapper modelMapper() 
	 {
    	 
		 log.info("AppConfig Class modelMapper() method is executed...");
		 //Creating ModelMapper Object
         ModelMapper modelMapper = new ModelMapper();
		 
		 //Use Strict Matching so that only properties with the exact name and type are mapped
		 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 
		 // Return the Model Mapper configured object
		 return  modelMapper;
	 }
}
