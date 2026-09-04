//CreateTranscationRequest .java(This class is responsible for sending the request to create a transaction. It contains the necessary fields and methods to encapsulate the transaction data that needs to be sent to the server for processing.)
package com.eshwar.pojo;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateTranscationRequest 
{
	
	private String userId;
    private Integer paymentMethodId;
    private Integer providerId;
    private Integer paymentTypeId;
    private BigDecimal amount;
    private String currency;
    private String merchantTransactionReference;
    
    
}
