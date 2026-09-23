//PayPalCreateOrderHelper .java(Helper Class)
package com.eshwar.service.helper;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.eshwar.constants.PayPalCreateOrderAPIConstants;
import com.eshwar.dto.PayPalCreateOrderRequest;
import com.eshwar.dto.PayPalCreateOrderRequest.Amount;
import com.eshwar.dto.PayPalCreateOrderRequest.ExperienceContext;
import com.eshwar.dto.PrepareHttpRequest;
import com.eshwar.pojo.CreateOrderRequest;
import com.eshwar.util.JsonAndJavaObjectUtilty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayPalCreateOrderHelper 
{
	
	private final JsonAndJavaObjectUtilty jsonAndJavaObjectUtilty ;

	@Value("${paypal.create-order.url}")
	private String paypalCreateOrderUrl;
	
	
	public PrepareHttpRequest preparingPayPalCreateOrderRequest(CreateOrderRequest createOrderRequest)
	{
		
		   log.info("PayPalCreateOrderHelper Class preparingPayPalCreateOrderRequest(---) method is Executed, createOrderRequest : {} ",createOrderRequest);
		   //creating custom Headers Class
			HttpHeaders customHeaders=new HttpHeaders();
		    
			customHeaders.add(PayPalCreateOrderAPIConstants.PAYPAL_REQUEST_ID , UUID.randomUUID().toString());
			customHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			//PayPalCreateOrderRequest Class Object Creation using Builder Design Pattern and setting some values to those fields
			ExperienceContext experienceContext = PayPalCreateOrderRequest.ExperienceContext.builder()
			        .paymentMethodPreference(
			        		PayPalCreateOrderAPIConstants.PAYMENT_METHOD_IMMEDIATE)
			        .landingPage(PayPalCreateOrderAPIConstants.LANDING_PAGE_LOGIN)
			        .shippingPreference(PayPalCreateOrderAPIConstants.SHIPPING_PREFERENCE_NO_SHIPPING)
			        .userAction(PayPalCreateOrderAPIConstants.USER_ACTION_PAY_NOW)
			        .returnUrl(createOrderRequest.getReturnUrl()
			                )
			        .cancelUrl(
			                createOrderRequest.getCancelUrl())
			        .build();
			
			
			
			Amount amount = PayPalCreateOrderRequest.Amount.builder()
			        .currencyCode(createOrderRequest.getCurrencyCode())
			        .value(String.valueOf(createOrderRequest.getAmount()))
			        .build();
			
			
			PayPalCreateOrderRequest paypalCreateOrderRequestObj =
			                PayPalCreateOrderRequest.builder()
			                  .intent(PayPalCreateOrderAPIConstants.INTENT_CAPTURE)
			                    .paymentSource(
			                        PayPalCreateOrderRequest.PaymentSource.builder()
			                                .paypal(
			                                        PayPalCreateOrderRequest.Paypal.builder()
			                                                .experienceContext(
			                                                        experienceContext
			                                                )
			                                                .build()
			                                )
			                                .build()
			                )
			                .purchaseUnits(
			                        List.of(
			                                PayPalCreateOrderRequest.PurchaseUnit.builder()
			                                        .amount(
			                                                amount
			                                        )
			                                        .build()
			                        )
			                )
			                .build();
			
			log.info("PayPalCreateOrderRequest Class Object Contains , paypalCreateOrderRequestObj : {} ",paypalCreateOrderRequestObj);
			
			 //For Converting PayPalCreateOrderRequest Class Object into JSON String We are calling javaObjectToJsonConversion(---) method of JsonAndJavaObjectUtilty  Class
			String jsonStringData=jsonAndJavaObjectUtilty.javaObjectToJsonConversion(paypalCreateOrderRequestObj);
			log.info("PayPalCreateOrderRequest Java Object into Json String , jsonStringData : {} ",jsonStringData);
			
			//preparing the request and returning the request
			 PrepareHttpRequest paypalCreateOrderHttpRequest = PrepareHttpRequest.builder()
					                        .httpMethod(HttpMethod.POST)
					                           .url(paypalCreateOrderUrl)
					                             .headers(customHeaders)
					                               .body(jsonStringData)
					                                 .build();

			 log.info("Final PayPal Create Order Http Request is , paypalCreateOrderHttpRequest : {} ",paypalCreateOrderHttpRequest);
		    return paypalCreateOrderHttpRequest;
	}

}
