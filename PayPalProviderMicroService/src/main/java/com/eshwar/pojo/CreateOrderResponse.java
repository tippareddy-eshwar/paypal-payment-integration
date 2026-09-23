// CreateOrderResponse .java(POJO Class)
package com.eshwar.pojo;
import lombok.Data;

@Data
public class CreateOrderResponse 
{
	
	private String orderId; 
	private String paypalStatus; 
	private String redirectUrl;
}
