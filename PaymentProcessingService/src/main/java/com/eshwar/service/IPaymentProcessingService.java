//IPaymentProcessingService .java
package com.eshwar.service;
import com.eshwar.pojo.CreateTranscationRequest;

public interface IPaymentProcessingService 
{
	
    public String createPayment(CreateTranscationRequest createTranscationRequest);
}
