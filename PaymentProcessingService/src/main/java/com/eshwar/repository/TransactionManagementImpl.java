// TransactionManagementImpl.java(implemented class for the ITransactionRepository interface)
package com.eshwar.repository;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

}
