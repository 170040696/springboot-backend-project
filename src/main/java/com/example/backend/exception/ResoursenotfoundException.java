package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResoursenotfoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResoursenotfoundException(String message)
	{
		super(message);
	}
}
