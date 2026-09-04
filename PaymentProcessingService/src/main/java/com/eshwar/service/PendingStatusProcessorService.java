//PendingStatusProcessorService.java(implemented class for ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import com.eshwar.dto.TranscationDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PendingStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus(TranscationDTO transcationDTO) 
	{
		log.info("PendingStatusProcessorService Class processStatus() method is executed , transcationDTO : {}", transcationDTO);
		return "Transcation Statuse - PENDING";
	}

}
