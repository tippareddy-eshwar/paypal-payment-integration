//IPaymentProcessingService .java
package com.eshwar.service;
import com.eshwar.pojo.CreatePaymentResponse;
import com.eshwar.pojo.CreateTranscationRequest;

public interface IPaymentProcessingService 
{
	
    public CreatePaymentResponse  createPayment(CreateTranscationRequest createTranscationRequest);
    
    public CreatePaymentResponse  intiatePayment(String transactionReference);
    
    public CreatePaymentResponse  capturePayment(String transactionReference);
}
