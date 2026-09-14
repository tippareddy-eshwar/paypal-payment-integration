//OrderServiceManagementImpl.java(implemented class of  IOrderServiceManagement interface
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.paypalclient.PayPalClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceManagementImpl implements IOrderServiceManagement 
{

	private final PayPalClient paypalClient;
	
	@Override
	public String createOrder() 
	{
		
		log.info("OrderServiceManagementImpl Class createOrder() method is Executed...");
		String response = paypalClient.makeCall();
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
