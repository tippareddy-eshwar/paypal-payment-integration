// ErrorCodeEnum .java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum ErrorCodeEnum 
{

	GENERIC_ERROR("30000","Unable to Process the Request. Please Try Again Later"),
	INVALID_NAME("30001","Name Can not be Empty"),
	UNKNOWN_EXTERNAL_SERVICE_OUTCOME("30002","Unable to determine the outcome of the external service request. Please try again later"),
    EXTERNAL_SERVICE_UNAVAILABLE("30003", "External service is temporarily unavailable. Please try again later"),
	GATEWAY_TIMEOUT("30004","External service did not respond within the expected time. Please try again later");
	
	private final String errorCode;
	private final String errorMessage;
	
	ErrorCodeEnum(String errorCode, String errorMessage) 
	{
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
	
	
}
