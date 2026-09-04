//CreatedStatusProcessorService.java(implemented class for the ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.repository.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatedStatusProcessorService implements ITranscationStatusProcessor 
{
	
	
	private final ModelMapper modelMapper;
	
	private final ITransactionRepository transactionRepository;

	@Override
	public String processStatus(TranscationDTO transcationDTO) 
	{
		log.info("CreatedStatusProcessor Class processStatus() method is executed, transcationDTO : {}", transcationDTO);
		//converting DTO into Entity using Model Mapper and pass to the repository layer to save into the database 
		Transaction entity = modelMapper.map(transcationDTO,Transaction.class);
		log.info("Converted TranscationDTO Object into Transaction Entity Object, entity : {}",entity);
		int transaction = transactionRepository.createTransaction(entity);
		log.info("Transaction is created in the database, transaction : {}", transaction);
		return "Transcation Statuse - CREATED";
	}

}
