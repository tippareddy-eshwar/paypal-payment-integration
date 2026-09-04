//PaymentProcessingServiceImpl.java(implemented class of IPaymentProcessingService interface)
package com.eshwar.service;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.pojo.CreateTranscationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingServiceImpl implements IPaymentProcessingService
{
	
	 private final PaymentStatusService paymentStatusService;
	
	
	public String createPayment(CreateTranscationRequest createTranscationRequest)
     {
    	   
		   log.info("PaymentProcessingServiceImpl Class createPayment() is executed , createTranscationRequest : {} ",createTranscationRequest);
		   TranscationDTO transcationDTO = prepareTranscationDTOFromRequest(createTranscationRequest);
		   transcationDTO.setTxnStatusId(1);//CREATED
		   
		   //calling the generateUniqueTransactionReference() method to generate unique transaction reference
		   String uniqueTransactionReference = generateUniqueTransactionReference();
		   log.info("Generated Unique Transaction Reference : {}",uniqueTransactionReference);
		   transcationDTO.setTxnReference(uniqueTransactionReference);
		   
    	   //calling the PaymentStatus Service Class processStatus Class
    	   String response = paymentStatusService.processStatus(transcationDTO);
    	   log.info("Response from the PaymentStatusProcessor Class response : {}",response);
    	   return response;
     }
	 
	 private String generateUniqueTransactionReference() 
	 {
		 log.info("PaymentProcessingServiceImpl Class  generateUniqueTransactionReference() method is executed ,");
		 return UUID.randomUUID().toString();
	 }
	 
	 private TranscationDTO prepareTranscationDTOFromRequest(CreateTranscationRequest createTranscationRequest) 
	 {
		 
		 log.info("PaymentProcessingServiceImpl Class  prepareTranscationDTOFromRequest(---`) method is executed , createTranscationRequest : {}",createTranscationRequest);
		 //creating the TranscationDTO Class Object
		 TranscationDTO transcationDTO = new TranscationDTO();
		 transcationDTO.setUserId(createTranscationRequest.getUserId());
		 transcationDTO.setPaymentMethodId(createTranscationRequest.getPaymentMethodId());
		 transcationDTO.setProviderId(createTranscationRequest.getProviderId());
		 transcationDTO.setPaymentTypeId(createTranscationRequest.getPaymentTypeId());
		 transcationDTO.setAmount(createTranscationRequest.getAmount());
		 transcationDTO.setCurrency(createTranscationRequest.getCurrency());
		 transcationDTO.setMerchantTransactionReference(createTranscationRequest.getMerchantTransactionReference());
		
		 //returning the transcationDTO object
		 return transcationDTO;
	 }
	 
}
