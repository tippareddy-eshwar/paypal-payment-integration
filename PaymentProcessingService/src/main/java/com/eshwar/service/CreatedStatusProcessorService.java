//CreatedStatusProcessorService.java(implemented class for the ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreatedStatusProcessorService extends AbstractTransactionStatusProcessor
{
	
	public CreatedStatusProcessorService(ModelMapper modelMapper , ITransactionRepository repository)
	{
		
		super(modelMapper,repository);
		log.info("CreatedStatusProcessorService Class Parameterized Constructor is Executed...");
	}
	
    @Override
	public TranscationDTO processStatusInternal(TranscationDTO transcationDTO) 
	{
		log.info("CreatedStatusProcessor Class processStatusInternal(---) method is executed, transcationDTO : {}", transcationDTO);
		//converting DTO into Entity using Model Mapper and pass to the repository layer to save into the database 
		Transaction entity = modelMapper.map(transcationDTO,Transaction.class);
		log.info("Converted TranscationDTO Object into Transaction Entity Object, entity : {}",entity);
		int transactionId = repository.createTransaction(entity);
		log.info("Transaction is created in the database, transactionId : {}", transactionId);
		//setting the generated primary key into the DTO object and return to the service layer
		transcationDTO.setId(transactionId);
		return transcationDTO;
	}

	

}
