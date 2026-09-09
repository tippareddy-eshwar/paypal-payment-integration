package com.eshwar.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusProcessorService extends AbstractTransactionStatusProcessor
{

   public InitiatedStatusProcessorService(ModelMapper modelMapper , ITransactionRepository repository)
	{
		
		super(modelMapper,repository);
		log.info("InitiatedStatusProcessorService Class Parameterized Constructor is Executed...");
	}		
	
	@Override
	public TranscationDTO processStatusInternal(TranscationDTO transcationDTO) 
	{
		
		log.info("InitiatedStatusProcessorService Class processStatusInternal(---) method is executed , TranscationDTO : {}",transcationDTO);
		//converting DTO into Entity using Model Mapper and pass to the repository layer to save into the database 
		Transaction transactionEntity = modelMapper.map(transcationDTO, Transaction.class);
		log.info("DTO into Entity , transactionEntity : {} ",transactionEntity);
		
		Boolean result = repository.updateTransactionDetails(transactionEntity);
		log.info("Result from the updateTransactionDetails(---) of Repository Class , result : {} ", result);
		if(!result)
		{
			log.error("Failed to Update the Transaction Details for the transaction id : {} ",transcationDTO.getId());
	        throw new RuntimeException("Failed to Update the Transaction Details for  the transaction id : "+transcationDTO.getId()) ;
		}
		
		log.info("Successfully Updated the Transaction Details  for the transaction id : {} ",transcationDTO.getId());
		return  transcationDTO;
	}

	

}
