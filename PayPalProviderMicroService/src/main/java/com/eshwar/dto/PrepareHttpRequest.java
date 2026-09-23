//PrepareHttpRequest .java
package com.eshwar.dto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrepareHttpRequest 
{
    
	private HttpMethod httpMethod;
	private String url;
	private HttpHeaders headers;
	private Object body;
	
}
