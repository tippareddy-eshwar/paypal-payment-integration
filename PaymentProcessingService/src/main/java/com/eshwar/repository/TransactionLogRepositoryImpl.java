//TransactionLogRepositoryImpl .java(implemented class for ITransactionLogRepository interface)
package com.eshwar.repository;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.eshwar.entity.TransactionLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionLogRepositoryImpl implements ITransactionLogRepository 
{

	private final NamedParameterJdbcTemplate template;
	@Override
	public int insert(TransactionLogEntity entity) 
	{
		
		log.info("TransactionLogRepositoryImpl Class insert(---) method is Executed , entity : {} ",entity);
		final String sql = """
			    INSERT INTO payments.`transaction_log` (transaction_id, txn_from_status, txn_to_status)
			    VALUES (:transactionId, :txnFromStatus, :txnToStatus)
			    """;
		
		//It will reads the properties of the given bean and maps them to the corresponding named parameters in the SQL statement. This allows you to pass an entire object (in this case, the transaction object) and have its properties automatically mapped to the named parameters in the SQL query.
		BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(entity);
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		try
		{
		   template.update(sql, params, keyHolder);
		  
		    Number generatedId = keyHolder.getKey();
		    
		    if(generatedId!=null)
		    {
		    	entity.setId(generatedId.intValue());
		    	log.info("One row is created successfully in the Transaction_Log Table with the  ID: {}", entity.getId());
		    }
		    else
		    {
		    	 log.error("Key Holder Does not contain a generated key after inserting a row into Transaction_Log Table...");
				 return -1; // Indicate failure
		    }
			
		}
		
		catch(Exception e)
		{
			 log.error("Error occurred while inserting a row into the Transaction_Log Table",e);
			 return -1; // Indicate failure
		}
		
		return entity.getId();
	}
     
	
}
