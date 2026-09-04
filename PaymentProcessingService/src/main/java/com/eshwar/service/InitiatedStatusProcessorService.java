package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus(TranscationDTO transcationDTO) 
	{
		
		log.info("InitiatedStatusProcessorService Class processStatus() method is executed , TranscationDTO : {}",transcationDTO);
		return "Transcation Statuse - INITIATED";
	}

}
