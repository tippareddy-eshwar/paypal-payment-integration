package com.eshwar.service;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusProcessorService implements ITranscationStatusProcessor 
{

	@Override
	public String processStatus() 
	{
		
		log.info("InitiatedStatusProcessorService Class processStatus() method is executed...");
		return "Transcation Statuse - INITIATED";
	}

}
