//OrderServiceManagementImpl.java(implemented class of  IOrderServiceManagement interface
package com.eshwar.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eshwar.dto.PrepareHttpRequest;
import com.eshwar.paypalclient.PayPalClient;
import com.eshwar.paypalcreateorderapi.PayPalCreateOrderResponse;
import com.eshwar.pojo.CreateOrderRequest;
import com.eshwar.pojo.CreateOrderResponse;
import com.eshwar.service.helper.PayPalCreateOrderHelper;
import com.eshwar.util.JsonAndJavaObjectUtilty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceManagementImpl implements IOrderServiceManagement 
{

	private final PayPalClient paypalClient;
	private final PayPalCreateOrderHelper createOrderHelper;
	private final JsonAndJavaObjectUtilty jsonAndJavaObjectUtilty ;
	
	@Override
	public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) 
	{
		
		      log.info("OrderServiceManagementImpl Class createOrder() method is Executed, createOrderRequest : {} ",createOrderRequest);
		
		      PrepareHttpRequest paypalCreateOrderHttpRequest =createOrderHelper.preparingPayPalCreateOrderRequest(createOrderRequest);
		      log.info("preparingPayPalCreateOrderRequest(---) method of PayPalCreateOrderHelper Class is returning,  paypalCreateOrderHttpRequest : {} ", paypalCreateOrderHttpRequest);
				 
			  //calling makeCall(---) method of PayPalClient Class
				 
		      ResponseEntity<String> paypalJsonResponse= paypalClient. makeCall(paypalCreateOrderHttpRequest);
		      log.info("Response from the makeCall() method of PayPalClient Class is, paypalJsonResponse : {} ",paypalJsonResponse);
		      
		      //For converting JSON into Java Class Object We are Calling jsonToJavaObjectConversion(---,---) method of JsonAndJavaObjectUtilty Class
		      PayPalCreateOrderResponse payPalCreateOrderResponseObject = jsonAndJavaObjectUtilty.jsonToJavaObjectConversion(paypalJsonResponse.getBody(), PayPalCreateOrderResponse.class);
		      log.info("After Converting JSON into PayPalCreateOrderResponse Class Object is ,  payPalCreateOrderResponseObject : {} ", payPalCreateOrderResponseObject);
		      
		     
		      String returnURL = payPalCreateOrderResponseObject.getLinks()
		                  .stream()
		                  .filter(link->"payer-action".equals(link.getRel()))
		                  .map(PayPalCreateOrderResponse.Link::getHref)
		                  .findFirst()
		                  .orElse(null);
		      
		      
		      //creating the  CreateOrderResponse Class Object
		      CreateOrderResponse createOrderResponse=new CreateOrderResponse ();
		      createOrderResponse.setOrderId(payPalCreateOrderResponseObject.getId());
		      createOrderResponse.setPaypalStatus(payPalCreateOrderResponseObject.getStatus());
		      createOrderResponse.setRedirectUrl(returnURL);
		      
		      log.info("CreateOrderResponse Class Object , createOrderResponse : {} ",createOrderResponse);
		                        
		      
		      return createOrderResponse;
	}
	
	
	@Override
	public String captureOrder(String  providerReference) 
	{
		
		log.info("OrderServiceManagementImpl Class captureOrder(---) method is Executed, providerReference : {} ",providerReference);
		return  "response from OrderServiceManagementImpl Class captureOrder() method...";
	}

}
