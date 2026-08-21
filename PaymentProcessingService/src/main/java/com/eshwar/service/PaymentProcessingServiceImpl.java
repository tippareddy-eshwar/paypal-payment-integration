//PaymentProcessingServiceImpl.java(implemented class of IPaymentProcessingService interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingServiceImpl implements IPaymentProcessingService
{
	
	
	private final PaymentStatusService paymentStatusService;
	
	 public String createPayment()
     {
    	   log.info("PaymentProcessingServiceImpl Class createPayment() is executed...");
    	   //String status="CREATED";
    	   //String status="INITIATED";
    	   //String status="PENDING";
    	   //String status="APPROVED";
    	   String status="SUCCESS";
    	   //String status="FAILED";
    	   //String status="UNDER-REVIEW";
    	   //calling the PaymentStatus Service Class processStatus Class
    	   String response = paymentStatusService.processStatus(status);
    	   log.info("Response from the PaymentStatusProcessor Class is: {}",response);
    	   return response;
     }
}
