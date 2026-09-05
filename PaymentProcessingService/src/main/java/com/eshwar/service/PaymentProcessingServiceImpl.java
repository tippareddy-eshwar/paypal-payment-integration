//PaymentProcessingServiceImpl.java(implemented class of IPaymentProcessingService interface)
package com.eshwar.service;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.eshwar.dto.TranscationDTO;
import com.eshwar.pojo.CreatePaymentResponse;
import com.eshwar.pojo.CreateTranscationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingServiceImpl implements IPaymentProcessingService
{
	
	 private final PaymentStatusService paymentStatusService;
	 
	 private final ModelMapper modelMapper;
	
	
	public CreatePaymentResponse  createPayment(CreateTranscationRequest createTranscationRequest)
     {
    	   
		   log.info("PaymentProcessingServiceImpl Class createPayment() is executed , createTranscationRequest : {} ",createTranscationRequest);
		   TranscationDTO transcationDTO = prepareTranscationDTOFromRequest(createTranscationRequest);
		   log.info("TranscationDTO Object is prepared from CreateTranscationRequest Object , transactionDTO : {}",transcationDTO);
		   transcationDTO.setTxnStatusId(1);//CREATED
		   
		   //calling the generateUniqueTransactionReference() method to generate unique transaction reference
		   String uniqueTransactionReference = generateUniqueTransactionReference();
		   log.info("Generated Unique Transaction Reference : {}",uniqueTransactionReference);
		   transcationDTO.setTxnReference(uniqueTransactionReference);
		   
    	   //calling the PaymentStatus Service Class processStatus Class
		   TranscationDTO response = paymentStatusService.processStatus(transcationDTO);
    	   log.info("Response from the PaymentStatusProcessor Class response : {}",response);
    	   
    	   //creating the CreatePaymentResponse Class Object
    	   CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse();
    	   if(response!=null)
    	   {
    		   
    		   //mapping TranscationDTO into CreatePaymentResponse 
    		   createPaymentResponse.setTxnReference(response.getTxnReference());
    		   createPaymentResponse.setTxnStatusId(response.getTxnStatusId());
    	   }
    	   
    	   
    	   return createPaymentResponse;
     }
	 
	 private String generateUniqueTransactionReference() 
	 {
		 log.info("PaymentProcessingServiceImpl Class  generateUniqueTransactionReference() method is executed ,");
		 return UUID.randomUUID().toString();
	 }
	 
	 

	 /*private TranscationDTO prepareTranscationDTOFromRequest(CreateTranscationRequest createTranscationRequest) 
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
	 }*/
	 
	 private TranscationDTO prepareTranscationDTOFromRequest(CreateTranscationRequest createTranscationRequest) 
	 {
		 
		 log.info("PaymentProcessingServiceImpl Class  prepareTranscationDTOFromRequest(---) method is executed , createTranscationRequest : {}",createTranscationRequest);
		 return modelMapper.map(createTranscationRequest, TranscationDTO.class);
	 
	 }
	 
	 
    
}
