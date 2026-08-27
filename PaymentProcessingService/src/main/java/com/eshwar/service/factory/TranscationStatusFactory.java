//TranscationStatusFactory.java(Factory Class)
package com.eshwar.service.factory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eshwar.service.ApprovedStatusProcessorService;
import com.eshwar.service.CreatedStatusProcessorService;
import com.eshwar.service.FailedStatusProcessorService;
import com.eshwar.service.ITranscationStatusProcessor;
import com.eshwar.service.InitiatedStatusProcessorService;
import com.eshwar.service.PendingStatusProcessorService;
import com.eshwar.service.SuccessStatusProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TranscationStatusFactory
{

	 private final ApplicationContext context;
	 
	 public ITranscationStatusProcessor getStatusProcessor(String paymentStatus)
	 {
		 log.info("TranscationStatusFactory Class getStatusProcessor(---) method is executed : {} ",paymentStatus);
		 if(paymentStatus==null)
		 {
			 throw new IllegalArgumentException("Payment Status Cannot Be Empty...");
		 }
		 
		 switch(paymentStatus.toUpperCase().trim())
		 {
		    
		    case "CREATED" :
		    	             //Dependency Look Up
		    	      return context.getBean(CreatedStatusProcessorService.class);
		    	         
		    case "INITIATED" :
		    	             //Dependency Look Up
   	                  return context.getBean(InitiatedStatusProcessorService.class);
   	                  
		    case "PENDING" :
		    	      //Dependency Look Up
   	                  return context.getBean(PendingStatusProcessorService.class);
   	                  
		    case "APPROVED" :
		    	      //Dependency Look Up
   	                  return context.getBean(ApprovedStatusProcessorService.class);
   	                  
		    case "SUCCESS" :
		    	      //Dependency Look Up
   	                  return context.getBean(SuccessStatusProcessorService.class);
   	                  
		    case "FAILED" :
		    	        //Dependency Look Up
   	                  return context.getBean(FailedStatusProcessorService.class);
   	                  
   	        default :
   	        	      throw new IllegalArgumentException("Invalid Payment Status : "+paymentStatus);
   	         
   	            
   	   }
	 }
}
