package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	//Add an exception handler for customer not found exceptn
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handelException(CustomerNotFoundException e)
	{
		
	//	create customer error response
		CustomerErrorResponse error=new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),
				                                                  e.getMessage(),System.currentTimeMillis());
		
		//return response entity
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND) ;
	}
	
	//add an exception for any random data entered for extracting data

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handelException(Exception e)
	{
		
	//	create customer error response
		CustomerErrorResponse error=new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
				                                                  e.getMessage(),System.currentTimeMillis());
		
		//return response entity
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST) ;
	}
	
}
