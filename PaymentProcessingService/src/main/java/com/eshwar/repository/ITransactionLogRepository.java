// ITransactionLogRepository .java
package com.eshwar.repository;
import com.eshwar.entity.TransactionLogEntity;

public interface ITransactionLogRepository 
{
   
	public int insert(TransactionLogEntity entity);
}
