//TransactionStatusConvertor.java
package com.eshwar.utility.modelmapper.converter.nametoid;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.TransactionStatusEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionStatusConvertor extends AbstractConverter<String,Integer>
{

	@Override
	protected Integer convert(String source)
	{
		
		log.info("TransactionStatusConvertor Class convert(---) method is Executed , source : {} ", source);
		TransactionStatusEnum transactionStatus = TransactionStatusEnum.getByName(source);
		log.info("TransactionStatusEnum.getByName(---) method is returning , transactionStatus : {} ",transactionStatus);
		return transactionStatus==null ? null : transactionStatus.getId();
	}

}
