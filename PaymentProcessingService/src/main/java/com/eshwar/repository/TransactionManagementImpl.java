// TransactionManagementImpl.java(implemented class for the ITransactionRepository interface)
package com.eshwar.repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.eshwar.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionManagementImpl implements ITransactionRepository 
{

	private final NamedParameterJdbcTemplate jdbcTemplate;
	@Override
	public int createTransaction(Transaction transaction) 
	{
		log.info("TransactionManagementImpl Class createTransaction() method is executed, transaction : {}", transaction);
		
		final String sql = """
	            INSERT INTO payments.`transaction`
	            (
	                user_id,
	                payment_method_id,
	                provider_id,
	                payment_type_id,
	                txn_status_id,
	                amount,
	                currency,
	                merchant_transaction_reference,
	                txn_reference,
	                provider_reference,
	                error_code,
	                error_message,
	                retry_count
	            )
	            VALUES
	            (
	                :userId,
	                :paymentMethodId,
	                :providerId,
	                :paymentTypeId,
	                :txnStatusId,
	                :amount,
	                :currency,
	                :merchantTransactionReference,
	                :txnReference,
	                :providerReference,
	                :errorCode,
	                :errorMessage,
	                :retryCount
	            )
	            """;
		 
		/* //MapSqlParameterSource is one of Spring's standard implementations for supplying named parameter values.
         MapSqlParameterSource parameters = new MapSqlParameterSource();
         parameters.addValue("userId",transaction.getUserId());
         parameters.addValue("paymentMethodId", transaction.getPaymentMethodId());
         parameters.addValue("providerId", transaction.getProviderId());
         parameters.addValue("paymentTypeId", transaction.getPaymentTypeId());
         parameters.addValue("txnStatusId", transaction.getTxnStatusId());
         parameters.addValue("amount", transaction.getAmount());
         parameters.addValue("currency", transaction.getCurrency());
         parameters.addValue("merchantTransactionReference",transaction.getMerchantTransactionReference());
         parameters.addValue("txnReference", transaction.getTxnReference());
         parameters.addValue("providerReference", transaction.getProviderReference());
         parameters.addValue("errorCode", transaction.getErrorCode());
         parameters.addValue("errorMessage", transaction.getErrorMessage());
         parameters.addValue("retryCount", transaction.getRetryCount()!=null?transaction.getRetryCount() : 0);*/
		
		//It will reads the properties of the given bean and maps them to the corresponding named parameters in the SQL statement. This allows you to pass an entire object (in this case, the transaction object) and have its properties automatically mapped to the named parameters in the SQL query.
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(transaction);
         
         
         KeyHolder keyHolder = new GeneratedKeyHolder();
         
         try
         {
        	 //this method will actually execute the query and insert the record into a database., with out this method we will not be able to insert the record into the database
        	 jdbcTemplate.update(sql, parameters, keyHolder);
			 Number generatedId = keyHolder.getKey();
			 
			 if (generatedId != null) 
			 {
				 transaction.setId(generatedId.intValue());
				 log.info("Transaction created successfully with ID: {}", transaction.getId());
			 } 
			 else 
			 {
				 log.error("Failed to retrieve generated ID for the transaction.");
				 return -1; // Indicate failure
			 }
         }
         
         catch (Exception e) 
		 {
			 log.error("Error occurred while creating transaction: {} ", e);
			 return -1; // Indicate failure
		 }
		 
		return transaction.getId();
	}
	
	
	@Override
	public Transaction getTransactionDetailsByTxnReference(String txnReference)
	{
		
		log.info("TransactionManagementImpl Class  getTransactionDetailsByTxnReference(---) method is Executed, txnReference : {} ",txnReference);
		
		 final String sql = """
			        SELECT
			            id,
			            user_id,
			            payment_method_id,
			            provider_id,
			            payment_type_id,
			            txn_status_id,
			            amount,
			            currency,
			            merchant_transaction_reference,
			            txn_reference,
			            provider_reference,
			            error_code,
			            error_message,
			            created_at,
			            updated_at,
			            retry_count
			        FROM payments.`transaction`
			        WHERE txn_reference = :txnReference
			        """;
		 
		 MapSqlParameterSource params= new MapSqlParameterSource();
		 params.addValue("txnReference", txnReference);
		 try
		 {
			 //queryForObject(---) method is used to Execute the SELECT query and map the result to a Transaction object
			 return jdbcTemplate.queryForObject(sql , params , new BeanPropertyRowMapper<>(Transaction.class));
		 }
		 
		 //No transaction details found for the given transaction reference
		 catch(EmptyResultDataAccessException ex)
		 {
			 log.info("No transaction found for txnReference : {}", txnReference);
			 return null;
		 }
		 
		 //Handle unexpected database or runtime exceptions
		 catch(Exception e)
		 {
			 log.error("Error occurred while fetching transaction details for txnReference : {}",txnReference, e );
			 return null;
		 }
		
	}


	@Override
	public Boolean updateTransactionDetails(Transaction transactionEntity) 
	{
		
		log.info("TransactionManagementImpl Class  updateTransactionDetails(---) method is Executed, transactionEntity : {} ",transactionEntity);
		
		if (transactionEntity == null) 
		{

		    log.warn("Transaction update failed, transaction entity is null , transactionEntity : {} ",transactionEntity) ;
		    return false;
		}

		if (transactionEntity.getId() == null) 
		{

		    log.warn("Transaction update failed, transaction ID is null, transactionEntity : {} ",transactionEntity);
		    return false;
		}
		
		log.info("Update Transaction Details Called for Id : {} ",transactionEntity.getId());
		
		 final String sql = """
			        UPDATE payments.`transaction` SET
			            txn_status_id = :txnStatusId,
			            provider_reference = :providerReference,
			            error_code = :errorCode,
			            error_message = :errorMessage,
			            retry_count = :retryCount
			        WHERE txn_reference = :txnReference
			        """;
		 
		 BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(transactionEntity);
		 
		 try
		 {
			 //update(---) is used to execute the UPDATE query
			 int rowsAffected = jdbcTemplate.update(sql,params);
			 log.info("No.Of Rows are Affected are ,rowsAffected : {} ", rowsAffected);
			 if(rowsAffected>0)
			 {
				 log.info("Transaction details updated successfully for the transaction Id : {}",transactionEntity.getId());
			     return true;    

			 }
			 
			 else
			 {
				 log.info("No Transaction details found for the transaction Id : {}",transactionEntity.getId());
			     return false;    

			 }
		 }
		 
		 catch(Exception e)
		 {
			 log.error("Error occurred while updating transaction details for the transaction Id : {}",transactionEntity.getId(),e);
			 return false;
		 }
		
		
	}

}
