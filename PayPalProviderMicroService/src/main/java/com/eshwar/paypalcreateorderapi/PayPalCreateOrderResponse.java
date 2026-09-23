package com.eshwar.paypalcreateorderapi;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PayPalCreateOrderResponse  //Outer Class
{

	    @JsonProperty("id")
	    private String id;

	    @JsonProperty("status")
	    private String status;

	    @JsonProperty("links")
	    private List<Link> links;

	    @Data
	    public static class Link  //Static Inner Class
	    {
            
	    	@JsonProperty("href")
	        private String href;

	    	@JsonProperty("rel")
	        private String rel;
            
	    	@JsonProperty("method")
	        private String method;
	    }
}
