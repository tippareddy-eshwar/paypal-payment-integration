//PaymentProcessingServiceImpl.java(implemented class of IPaymentProcessingService interface)
package com.eshwar.service;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.eshwar.constants.TransactionStatusEnum;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.pojo.CreatePaymentResponse;
import com.eshwar.pojo.CreateTranscationRequest;
import com.eshwar.repository.ITransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingServiceImpl implements IPaymentProcessingService
{
	
	 private final PaymentStatusService paymentStatusService;
	 
	 private final ModelMapper modelMapper;
	 
	 private final  ITransactionRepository transcationRepo;
	
	
	public CreatePaymentResponse  createPayment(CreateTranscationRequest createTranscationRequest)
     {
    	   
		   log.info("PaymentProcessingServiceImpl Class createPayment() is executed , createTranscationRequest : {} ",createTranscationRequest);
		   TranscationDTO transcationDTO = prepareTranscationDTOFromRequest(createTranscationRequest);
		   log.info("TranscationDTO Object is prepared from CreateTranscationRequest Object , transactionDTO : {}",transcationDTO);
		   transcationDTO.setTxnStatus(TransactionStatusEnum.CREATED.getName());//CREATED
		   
		   //calling the generateUniqueTransactionReference() method to generate unique transaction reference
		   String uniqueTransactionReference = generateUniqueTransactionReference();
		   log.info("Generated Unique Transaction Reference : {}",uniqueTransactionReference);
		   transcationDTO.setTxnReference(uniqueTransactionReference);
		   
    	   //calling the PaymentStatus Service Class processStatus Class
		   TranscationDTO response = paymentStatusService.processStatus(transcationDTO);
    	   log.info("Response from the PaymentStatusProcessor Class after processing INITIATED Status, response : {}",response);
    	   
    	   //creating the CreatePaymentResponse Class Object
    	   CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse();
    	   if(response!=null)
    	   {
    		   
    		   //mapping TranscationDTO into CreatePaymentResponse 
    		   createPaymentResponse.setTxnReference(response.getTxnReference());
    		   createPaymentResponse.setTxnStatus(response.getTxnStatus());
    	   }
    	   
    	   log.info("Payment Response , createPaymentResponse : {} ",createPaymentResponse);
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

	 @Override
	 public CreatePaymentResponse intiatePayment(String transactionReference)
	 {
		
		 log.info("PaymentProcessingServiceImpl Class intiatePayment(---) method is executed ,transactionReference : {}",transactionReference);
		
		 //calling the getTransactionDetailsByTxnReference(---) method of  ITransactionRepository  interface implemented class
		 Transaction transactionEntity = transcationRepo.getTransactionDetailsByTxnReference(transactionReference);
		 
		 log.info("TransactionManagementImpl Class getTransactionDetailsByTxnReference(---) is returning , transactionEntity : {} ", transactionEntity);
		 
		 //using and Converters and Model Mapper  for Converting Entity into DTo
		 TranscationDTO transactionDTo = modelMapper.map(transactionEntity,TranscationDTO.class);
		 log.info("From Entity to DTO , transactionDTo : {} ",transactionDTo);
		 
		 transactionDTo.setTxnStatus(TransactionStatusEnum.INITIATED.getName());
		 
		 //calling the PaymentStatus Service Class processStatus Class
		 TranscationDTO response = paymentStatusService.processStatus(transactionDTo);
		 log.info("Response from the PaymentStatusProcessor Class after processing INITIATED Status , response : {}",response);
		 
		 
		   //we need to call PayPal APIs(TO DO)
		 
		     transactionDTo.setTxnStatus(TransactionStatusEnum.PENDING.getName());
		     transactionDTo.setProviderReference("PayPalOrder12345");//dummy value
		     
		     
			//calling the PaymentStatus Service Class processStatus Class
			  response = paymentStatusService.processStatus(transactionDTo);
			 log.info("Response from the PaymentStatusProcessor Class after processing PENDING Status , response : {}",response);
		 
		 
		 //returning PaymentResponse Class Object
		 CreatePaymentResponse paymentResponse=new CreatePaymentResponse();
		 
		 paymentResponse.setTxnReference(response.getTxnReference());
		 paymentResponse.setTxnStatus(response.getTxnStatus());
		 
		 log.info("Payment Response , paymentResponse : {} ",paymentResponse);
		 
		 return paymentResponse;
	 }

	 @Override
	 public CreatePaymentResponse capturePayment(String transactionReference)
	 {
		
		 log.info("PaymentProcessingServiceImpl Class capturePayment(---) method is executed ,transactionReference : {}",transactionReference);
			//returning PaymentResponse Class Object(As of now Dummy Response Class Object is returning)
		return null;
	 }
	 
	 
	 
    
}
