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
	 
	 public ITranscationStatusProcessor getStatusProcessor(Integer paymentStatusId)
	 {
		 log.info("TranscationStatusFactory Class getStatusProcessor(---) method is executed, paymentStatusId {} ",paymentStatusId);
		 if(paymentStatusId==null)
		 {
			 throw new IllegalArgumentException("Payment Status Cannot Be Empty...");
		 }
		 
		 switch(paymentStatusId)
		 {
		    
		    case 1 :
		    	             //Dependency Look Up
		    	      return context.getBean(CreatedStatusProcessorService.class);
		    	         
		    case 2 :
		    	             //Dependency Look Up
   	                  return context.getBean(InitiatedStatusProcessorService.class);
   	                  
		    case 3 :
		    	      //Dependency Look Up
   	                  return context.getBean(PendingStatusProcessorService.class);
   	                  
		    case 4 :
		    	      //Dependency Look Up
   	                  return context.getBean(ApprovedStatusProcessorService.class);
   	                  
		    case 5 :
		    	      //Dependency Look Up
   	                  return context.getBean(SuccessStatusProcessorService.class);
   	                  
		    case 6 :
		    	        //Dependency Look Up
   	                  return context.getBean(FailedStatusProcessorService.class);
   	                  
   	        default :
   	        	      throw new IllegalArgumentException("Invalid Payment Status : "+paymentStatusId);
   	         
   	            
   	   }
	 }
}
