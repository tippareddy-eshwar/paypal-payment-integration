//TransactionStatusIdToNameConvertor.java
package com.eshwar.utility.modelmapper.converter.idtoname;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.TransactionStatusEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionStatusIdToNameConvertor extends AbstractConverter<Integer,String>
{

	@Override
	protected String convert(Integer source)
	{
		
		log.info("TransactionStatusIdToNameConvertor Class convert(---) method is Executed , source : {} ", source);
		TransactionStatusEnum transactionStatus = TransactionStatusEnum.getById(source);
		log.info("TransactionStatusEnum.getById(---) method is returning , transactionStatus : {} ",transactionStatus);
		return transactionStatus==null ? null : transactionStatus.getName();
	}

}
