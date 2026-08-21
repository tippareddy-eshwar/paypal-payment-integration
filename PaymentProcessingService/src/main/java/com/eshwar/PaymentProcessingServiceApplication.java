//PaymentProcessingServiceApplication .java(Client Application)
package com.eshwar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentProcessingServiceApplication 
{

	public static void main(String[] args) 
	{
		//BootStrapping the IOC Container
		SpringApplication.run(PaymentProcessingServiceApplication.class, args);
	}

}
