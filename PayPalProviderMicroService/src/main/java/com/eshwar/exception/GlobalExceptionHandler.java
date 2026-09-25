package com.eshwar.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.eshwar.constants.ErrorCodeEnum;
import com.eshwar.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler 
{

	
	@ExceptionHandler(PayPalProviderException.class)
	public ResponseEntity<ErrorResponse> handlePayPalProviderException(PayPalProviderException paypalProviderException)
	{
		
		log.error("PayPalProviderException occurred, errorCode={}, errorMessage={}, httpStatus={}",
	                                                                        paypalProviderException.getErrorCode(),
	                                                                        paypalProviderException.getErrorMessage(),
	                                                                        paypalProviderException.getHttpStatus()
	                                                                        , paypalProviderException);
	       
		//Creating the Error Response Class Object
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(paypalProviderException.getErrorCode());
		errorResponse.setErrorMessage(paypalProviderException.getErrorMessage());
		
		//Returning ResposeEntity<ErrorResponse> ClassObject
		return new ResponseEntity<ErrorResponse>(errorResponse,paypalProviderException.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception e)
	{
		
		log.error("Unexpected exception occurred while processing the request",e);
		
		//Creating the Error Response Class Object
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ErrorCodeEnum.GENERIC_ERROR.getErrorCode());
		errorResponse.setErrorMessage(ErrorCodeEnum.GENERIC_ERROR.getErrorMessage());
		
		//Returning ResposeEntity<ErrorResponse> ClassObject
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
