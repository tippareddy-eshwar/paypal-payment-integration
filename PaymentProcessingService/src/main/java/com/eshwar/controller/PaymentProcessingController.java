package com.eshwar.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eshwar.pojo.CreatePaymentResponse;
import com.eshwar.pojo.CreateTranscationRequest;
import com.eshwar.service.IPaymentProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/payments")//Global Path (or) Global Request Path
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingController 
{
	
	 private final IPaymentProcessingService paymentProcessingService;
	 
	 @PostMapping //method path (or) method request path
     public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreateTranscationRequest createTranscationRequest)
     {
    	
		 log.info(" PaymentProcessingController Class createPayment() method is executed, createTranscationRequest : {} ",createTranscationRequest);
    	 //calling the PaymentProcessingService Class createPayment() method
    	 CreatePaymentResponse response = paymentProcessingService.createPayment(createTranscationRequest);
    	 log.info("Response from the PaymentProcessingService Class is response: {}",response);
    	 //returning ResponseEntity Class Object
    	 return new ResponseEntity<CreatePaymentResponse>(response,HttpStatus.CREATED);
    	 
    	 //return ResponseEntity.status(HttpStatus.CREATED).body(response);//builder design pattern
     }
	 
	 @PostMapping("/{txnReference}/initiate") //method path (or) method request path
     public ResponseEntity<CreatePaymentResponse> initiatePayment(@PathVariable("txnReference") String txnReference)
     {
    	 log.info("PaymentProcessingController Class initiatePayment() method is executed , txnReference : {} " , txnReference);
    	 
    	 //calling the PaymentProcessingService Class initiatePayment(---) method
    	 CreatePaymentResponse intiatePaymentResponse = paymentProcessingService.intiatePayment(txnReference);
    	 
    	 //returning ResponseEntity Class Object
    	 return new ResponseEntity<CreatePaymentResponse>(intiatePaymentResponse,HttpStatus.OK);
    	 //return ResponseEntity.status(HttpStatus.CREATED).body(response);//builder design pattern
     }
	 
	 @PostMapping("/{txnReference}/capture") //method path (or) method request path
     public ResponseEntity<String> capturePayment(@PathVariable("txnReference") String txnReference)
     {
    	 log.info("PaymentProcessingController Class capturePayment() method is executed ,  txnReference : {} " , txnReference);
    	 //returning ResponseEntity Class Object
    	 return new ResponseEntity<String>("Capture Payment Process Started : "+txnReference, HttpStatus.OK);
    	 //return ResponseEntity.status(HttpStatus.CREATED).body(response);//builder design pattern
     }
}
