//PendingStatusProcessorService.java(implemented class for ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PendingStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus() 
	{
		log.info("PendingStatusProcessorService Class processStatus() method is executed...");
		return "Transcation Statuse - PENDING";
	}

}
