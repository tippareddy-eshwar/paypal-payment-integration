//OrderController.java(Rest Controller Class)
package com.eshwar.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshwar.pojo.CreateOrderRequest;
import com.eshwar.pojo.CreateOrderResponse;
import com.eshwar.service.IOrderServiceManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/orders")//Global Path (or) Global Request Path
@RequiredArgsConstructor
@Slf4j
public class OrderController 
{
	
    private final IOrderServiceManagement service;
    
    
	@PostMapping
    public ResponseEntity< CreateOrderResponse>createOrder(@RequestBody CreateOrderRequest createOrderRequest)
    {
    	
    	log.info("OrderController Class createOrder() method is Executed, createOrderRequest : {} ",createOrderRequest);
    	return new ResponseEntity<CreateOrderResponse>(service.createOrder(createOrderRequest),HttpStatus.CREATED);
    }
	
	@GetMapping("/{providerRefrence}/capture")//method path (or) method request path
	public ResponseEntity<String>captureOrder(@PathVariable String providerRefrence)
	{
		log.info("OrderController Class captureOrder(---) method is Executed...");
    	return new ResponseEntity<String>(service.captureOrder(providerRefrence),HttpStatus.OK);
	}
}
