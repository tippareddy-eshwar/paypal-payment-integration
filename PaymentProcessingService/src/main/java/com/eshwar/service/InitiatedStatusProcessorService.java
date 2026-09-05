package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public TranscationDTO  processStatus(TranscationDTO transcationDTO) 
	{
		
		log.info("InitiatedStatusProcessorService Class processStatus(---) method is executed , TranscationDTO : {}",transcationDTO);
		return  transcationDTO;
	}

}
