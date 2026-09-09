//ApprovedStatusProcessorService .java(implemented class of ITranscationStatusProcessor interface)
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
public class ApprovedStatusProcessorService implements ITranscationStatusProcessor
{

	private final ModelMapper modelMapper;
    private final ITransactionRepository repository;
    
    
	@Override
	public TranscationDTO  processStatus(TranscationDTO transcationDTO)
	{
		
		         log.info("ApprovedStatusProcessorService Class processStatus(---) method is executed , transcationDTO : {}", transcationDTO);
		         
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
