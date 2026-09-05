//PaymentStatusService.java(This class is deals with all status processing)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import com.eshwar.service.factory.TranscationStatusFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusService
{
	
	 private final TranscationStatusFactory factory;
	 
     public TranscationDTO processStatus(TranscationDTO transcationDTO)
     {
    	 log.info("PaymentStatusService Class processStatus(---) method is executed, transcationDTO {}: ", transcationDTO);
    	 
    	 ITranscationStatusProcessor statusProcessor = factory.getStatusProcessor(transcationDTO.getTxnStatusId());
    	 //calling the processStatus() method
    	 TranscationDTO responseDTO = statusProcessor.processStatus(transcationDTO);
    	 
    	 log.info("Response From ITransactionStatusProcessor implemented classes : {} ",responseDTO.getClass().getSimpleName());
    	 return responseDTO; 
     }
}
