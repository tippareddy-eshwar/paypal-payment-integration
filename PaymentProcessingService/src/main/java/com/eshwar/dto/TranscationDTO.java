//TranscationDTO .java (DTO Class is used to transfer data between different layers of the application. It acts as a container for the transaction-related data that needs to be sent or received, allowing for a clean separation of concerns and facilitating communication between different components of the system)
package com.eshwar.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TranscationDTO 
{
   
	private Integer id;
    private String userId;
    private Integer paymentMethodId;
    private Integer providerId;
    private Integer paymentTypeId;
    private Integer txnStatusId;
    private BigDecimal amount;
    private String currency;
    private String merchantTransactionReference;
    private String txnReference;
    private String providerReference;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer retryCount;
}
