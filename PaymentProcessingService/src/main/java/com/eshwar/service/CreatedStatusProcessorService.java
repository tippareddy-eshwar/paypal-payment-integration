//CreatedStatusProcessorService.java(implemented class for the ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreatedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus(TranscationDTO transcationDTO) 
	{
		log.info("CreatedStatusProcessor Class processStatus() method is executed, transcationDTO : {}", transcationDTO);
		return "Transcation Statuse - CREATED";
	}

}
