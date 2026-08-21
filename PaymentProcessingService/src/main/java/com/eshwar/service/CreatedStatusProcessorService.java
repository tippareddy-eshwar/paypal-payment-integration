//CreatedStatusProcessorService.java(implemented class for the ITranscationStatusProcessor interface)
package com.eshwar.service;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreatedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus() 
	{
		log.info("CreatedStatusProcessor Class processStatus() method is executed...");
		return "Transcation Statuse - CREATED";
	}

}
