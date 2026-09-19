//OrderServiceManagementImpl.java(implemented class of  IOrderServiceManagement interface
package com.eshwar.service;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.eshwar.dto.PayPalCreateOrderRequest;
import com.eshwar.dto.PrepareHttpRequest;
import com.eshwar.paypalclient.PayPalClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceManagementImpl implements IOrderServiceManagement 
{

	private final PayPalClient paypalClient;
	private final ObjectMapper mapper;
	
	@Override
	public String createOrder() 
	{
		
		log.info("OrderServiceManagementImpl Class createOrder() method is Executed...");
		
		
		       //creating custom Headers Class
				HttpHeaders customHeaders=new HttpHeaders();
	            
				customHeaders.add("PayPal-Request-Id", UUID.randomUUID().toString());
				customHeaders.setContentType(MediaType.APPLICATION_JSON);
				
				//PayPalCreateOrderRequest Class Object Creation using Builder Design Pattern and setting some values to those fields
				PayPalCreateOrderRequest paypalCreateOrderRequestObj =
				                PayPalCreateOrderRequest.builder()
				                  .intent("CAPTURE")
				                    .paymentSource(
				                        PayPalCreateOrderRequest.PaymentSource.builder()
				                                .paypal(
				                                        PayPalCreateOrderRequest.Paypal.builder()
				                                                .experienceContext(
				                                                        PayPalCreateOrderRequest.ExperienceContext.builder()
				                                                                .paymentMethodPreference(
				                                                                        "IMMEDIATE_PAYMENT_REQUIRED")
				                                                                .landingPage("LOGIN")
				                                                                .shippingPreference("NO_SHIPPING")
				                                                                .userAction("PAY_NOW")
				                                                                .returnUrl(
				                                                                        "https://example.com/returnUrl")
				                                                                .cancelUrl(
				                                                                        "https://example.com/cancelUrl")
				                                                                .build()
				                                                )
				                                                .build()
				                                )
				                                .build()
				                )
				                .purchaseUnits(
				                        List.of(
				                                PayPalCreateOrderRequest.PurchaseUnit.builder()
				                                        .amount(
				                                                PayPalCreateOrderRequest.Amount.builder()
				                                                        .currencyCode("USD")
				                                                        .value("1.00")
				                                                        .build()
				                                        )
				                                        .build()
				                        )
				                )
				                .build();
				
				log.info("PayPalCreateOrderRequest Class Object Contains , paypalCreateOrderRequestObj : {} ",paypalCreateOrderRequestObj);
				
				String jsonStringData=null;
				
				try
				{
				  //Converting PayPalCreateOrderRequest Class Object into JSON String
				  jsonStringData= mapper.writeValueAsString(paypalCreateOrderRequestObj);
				  log.info("Java Object into Json String , jsonStringData : {} ",jsonStringData);
				} 
				
				catch (Exception e)
				{

				    log.error("Failed to convert PayPalCreateOrderRequest object to JSON", e);

				}
				
				
				//preparing the request and returning the request
				 PrepareHttpRequest paypalCreateOrderHttpRequest = PrepareHttpRequest.builder()
						                        .httpMethod(HttpMethod.POST)
						                           .url("https://api-m.sandbox.paypal.com/v2/checkout/orders")
						                             .headers(customHeaders)
						                               .body(jsonStringData)
						                                 .build();
		
				 log.info("Finnal PayPal Create Order Http Request is , paypalCreateOrderHttpRequest : {} ",paypalCreateOrderHttpRequest);
				 
				 //calling makeCall(---) method of PayPalClient Class
				 
		         String response = paypalClient. makeCall(paypalCreateOrderHttpRequest);
		         log.info("Response from the makeCall() method of PayPalClient Class is : {} ",response);
		         return  response;
	}

	@Override
	public String captureOrder(String  providerReference) 
	{
		
		log.info("OrderServiceManagementImpl Class captureOrder(---) method is Executed, providerReference : {} ",providerReference);
		return  "response from OrderServiceManagementImpl Class captureOrder() method...";
	}

}
