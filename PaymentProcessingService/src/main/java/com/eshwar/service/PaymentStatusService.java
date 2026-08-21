//PaymentStatusService.java(This class is deals with all status processing)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.service.factory.TranscationStatusFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusService
{
	
	 private final TranscationStatusFactory factory;
	 
     public String processStatus(String paymentStatus)
     {
    	 log.info("PaymentStatusService Class processStatus(---) method is executed {}: ", paymentStatus);;
    	 
    	 ITranscationStatusProcessor statusProcessor = factory.getStatusProcessor(paymentStatus);
    	 //calling the processStatus() method
    	 String processStatus = statusProcessor.processStatus();
    	 
    	 log.info("Response From : {} ",processStatus.getClass().getSimpleName());
    	 return "From PaymentStatusService Class processStatus(---) method "+processStatus;
     }
}
