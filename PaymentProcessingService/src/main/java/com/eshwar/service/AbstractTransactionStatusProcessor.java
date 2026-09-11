//AbstractTransactionStatusProcessor.java (abstract class)
package com.eshwar.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.eshwar.constants.TransactionStatusEnum;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.entity.TransactionLogEntity;
import com.eshwar.repository.ITransactionRepository;
import com.eshwar.repository.TransactionLogRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTransactionStatusProcessor implements ITranscationStatusProcessor 
{

	protected final ModelMapper modelMapper;
    protected final ITransactionRepository repository;
    private List<TransactionStatusEnum>finalStatus=List.of(TransactionStatusEnum.SUCCESS, TransactionStatusEnum.FAILED);
    
    @Autowired
    private  TransactionLogRepositoryImpl transactionLogRepository;
    
    public AbstractTransactionStatusProcessor(ModelMapper modelMapper, ITransactionRepository repository)
    {
    	
    	log.info("AbstractTransactionStatusProcessor Parameterized Constructor is Executed...");
    	
    	this.modelMapper=modelMapper;
    	this.repository=repository;
    }
	
    
	@Override
	public TranscationDTO processStatus(TranscationDTO newTranscationDTO) 
	{
		
		log.info("AbstractTransactionStatusProcessor Class EprocessStatus(---) method is Executed , newTranscationDTO : {} ",newTranscationDTO);
		
		
		//we need to make the DB Call to get the previous row on top that you can reterive the status from the row
		Transaction existingEntity = repository.getTransactionDetailsByTxnReference(newTranscationDTO.getTxnReference());
		log.info("Existing Entity is ,  existingEntity : {} ", existingEntity);
		
		//from existing entity get status of it
		TransactionStatusEnum existingStatusEnum=existingEntity !=null ? TransactionStatusEnum.getById(existingEntity.getTxnStatusId()) : null;
		log.info("Existing Status Enum is ,  existingStatusEnum : {} ", existingStatusEnum);
		
		//getting the latest status 
		
		TransactionStatusEnum newStatusEnum = TransactionStatusEnum.getByName(newTranscationDTO.getTxnStatus());
		
		log.info("New Status Enum is ,  newStatusEnum : {} ", newStatusEnum);
		
		//step1 : If Previous status and New Status is Same Don't Update
		
		CheckSameStausAndExist(newTranscationDTO, existingStatusEnum, newStatusEnum);
		
		//step 2 : If the existing status is in final state that is either in success (or) failed then don't update
		
		checkExistingFinalStausAndExist(newTranscationDTO, existingStatusEnum);
		
		//step 3 : Process Current Status
		
		log.info("Step 3 Execution Started ....");
		
		 newTranscationDTO=processStatusInternal(newTranscationDTO);
		
		log.info("Step 3 Execution Ended ....");
		
		//step 4 : Insert a row into the transaction log table if the status is changed from one status into another status
		log.info("transactionLogRepository : {}", transactionLogRepository);
		
		//creating the TransactionLogEntity Class Object using Builder Design Pattern
		logTransactionChange(newTranscationDTO, existingStatusEnum, newStatusEnum);
		
		//step 5 :Raise a event to the kafka
		
		
		
		log.info("Successfully Processed and Returning Final DTO : {} ",  newTranscationDTO);
		
		return  newTranscationDTO;
	}


	private void logTransactionChange(TranscationDTO newTranscationDTO, TransactionStatusEnum existingStatusEnum,TransactionStatusEnum newStatusEnum) 
	{
		log.info("AbstractTransactionStatusProcessor Class logTransactionChange(---,---,---) method is Executed...");
		TransactionLogEntity logEntity= TransactionLogEntity.builder()
				                           //transactionId is the primary key of main table that is Transaction Table
				                           .transactionId(newTranscationDTO.getId())
				                              .txnFromStatus(
				                            		  existingStatusEnum!=null?existingStatusEnum.getName():"Not Applicable")
				                                  .txnToStatus(newStatusEnum .getName())
				                                     .build();
		
		int pk = transactionLogRepository.insert(logEntity);
		log.info("Row is inserted into the Transaction Log Table With the id : {} ",pk);
	}


	private void checkExistingFinalStausAndExist(TranscationDTO newTranscationDTO,TransactionStatusEnum existingStatusEnum) 
	{
		log.info("AbstractTransactionStatusProcessor Class checkExistingFinalStausAndExist(---,---,---) method is Executed...");
		if(existingStatusEnum!=null && finalStatus.contains(existingStatusEnum))
		{
			log.error("Previous status is in final state(either in success or failed) for the transactionReference : {} ", newTranscationDTO.getTxnReference());
			throw new RuntimeException("Previous status is in final state(either in success or failed) for the transactionReference : "+newTranscationDTO.getTxnReference()+ "So updation is not possible");
		}
	}


	private void CheckSameStausAndExist(TranscationDTO newTranscationDTO, TransactionStatusEnum existingStatusEnum,TransactionStatusEnum newStatusEnum)
	{
		log.info("AbstractTransactionStatusProcessor Class checkSameStausAndExist(---,---,---) method is Executed...");
		if(existingStatusEnum!=null && existingStatusEnum==newStatusEnum)
		{
			  
			log.error("Existing Staus and New Staus both are Same for the transactionReference : {} ", newTranscationDTO.getTxnReference());
			throw new RuntimeException("Existing Staus and New Staus both are Same for the transactionReference : "+newTranscationDTO.getTxnReference());
		}
	}
	
	
	public abstract TranscationDTO processStatusInternal(TranscationDTO transcationDTO) ;

}
