//PayPalProviderMicroServiceApplication.java(Main Application)
package com.eshwar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayPalProviderMicroServiceApplication
{

	public static void main(String[] args)
	{
		//Bootstrapping the IOC Container
		SpringApplication.run(PayPalProviderMicroServiceApplication.class, args);
	}

}
