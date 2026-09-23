// JsonAndJavaObjectUtilty .java
package com.eshwar.util;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonAndJavaObjectUtilty 
{

	private final ObjectMapper mapper;
	
	public <T> T jsonToJavaObjectConversion(String json, Class<T> clazz)
	{
		
		log.info("JsonAndJavaObjectUtilty Class  jsonToJavaObjectConversion(---,---) method is Executed , json, clazz : {} ",json , clazz);
		try
	      {
	        
	    	 //converting JSON into Java Class Object
	    	 return mapper.readValue(json, clazz);
	      }
	      
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	    	  throw new RuntimeException("Failed to Parse From JSON into Java Object...");
	      }
	}
	
	
	public String javaObjectToJsonConversion(Object obj)
	{
		
		log.info("JsonAndJavaObjectUtilty Class  javaObjectToJsonConversion(---) method is Executed , obj : {} ",obj);
		try
	      {
	        
	    	 //converting Java Class Object into JSON
	    	 return mapper.writeValueAsString(obj);	     
	     }
	      
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	    	  throw new RuntimeException("Failed to Parse From Java Object into JSON...");
	      }
	}
}
