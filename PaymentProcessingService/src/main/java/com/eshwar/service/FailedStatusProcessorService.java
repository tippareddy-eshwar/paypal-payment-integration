//FailedStatusProcessorService.java(implemented class forITranscationStatusProcessor interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FailedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public TranscationDTO  processStatus(TranscationDTO transcationDTO) 
	{
		log.info("FailedStatusProcessorService Class processStatus(---) method is executed , transcationDTO : {}", transcationDTO);
		return transcationDTO;
	}

}
