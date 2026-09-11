// TransactionLogEntity.java(Entity Class)
package com.eshwar.entity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionLogEntity 
{
    
	    //primary key for this table
	    private Integer id;

	    //foreign key , it is the primary key for some of the table
	    private Integer transactionId;

	    private String txnFromStatus;

	    private String txnToStatus;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;
}
