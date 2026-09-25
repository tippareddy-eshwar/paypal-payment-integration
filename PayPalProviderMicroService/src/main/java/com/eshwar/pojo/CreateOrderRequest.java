package com.eshwar.pojo;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateOrderRequest 
{

	   private String returnUrl;
	   
	   private String cancelUrl;
	   
	   private String currencyCode;
	   
	   private BigDecimal amount;
}
