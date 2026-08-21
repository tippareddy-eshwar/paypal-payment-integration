package com.eshwar.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eshwar.service.IPaymentProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment-processing-api")//Global Path (or) Global Request Path
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingController 
{
	
	 private final IPaymentProcessingService paymentProcessingService;
	 
	 @PostMapping("/create-payment")//method path (or) method request path
     public ResponseEntity<String> createPayment()
     {
    	 log.info(" PaymentProcessingController Class createPayment() method is executed...");
    	 //calling the PaymentProcessingService Class createPayment() method
    	 String response = paymentProcessingService.createPayment();
    	 //returning ResponseEntity Class Object
    	 return new ResponseEntity<String>(response,HttpStatus.CREATED);
     }
}
