// ITransactionRepository.java
package com.eshwar.repository;
import com.eshwar.entity.Transaction;
public interface ITransactionRepository
{

	 public int createTransaction(Transaction transaction);
	 
	 public Transaction getTransactionDetailsByTxnReference(String txnReference);
	 
	 public Boolean updateTransactionDetails(Transaction transactionEntity);
}
