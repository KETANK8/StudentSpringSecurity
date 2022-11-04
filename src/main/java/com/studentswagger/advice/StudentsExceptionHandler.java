package com.studentswagger.advice;

import java.util.HashMap;
import java.util.Map;

import com.studentswagger.exception.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentsExceptionHandler {

	// Handaling exception raised by data validation
	// if user input invalid data exception raised and handle
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> exceptionHandler(MethodArgumentNotValidException e) {

		Map<String, String> errorMap = new HashMap<>();

		e.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});

		return errorMap;
	}

	// handaling the thrown global exception
	// if invalid data is entered by user or data not found in database
	// a global exception is created and handle in globalExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(GlobalException.class)
	public Map<String, String> globalExceptionHandler(GlobalException gex) {

		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("Exception ", gex.getMessage());

		return errorMap;
	}
}
