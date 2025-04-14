// package com.omercngoktas.exception;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     private List<String> addMapValue(String mapValue, List<String> mapList) {
//         mapList.add(mapValue);
//         return mapList;
//     }

//     @ExceptionHandler(value = MethodArgumentNotValidException.class)
//     public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//         Map<String, List<String>> errorMap = new HashMap<>();

//         for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
//             String fieldName = ((FieldError) objError).getField();
//             String errorMessage = objError.getDefaultMessage();

//             if (errorMap.containsKey(fieldName)) {
//                 errorMap.get(fieldName).add(errorMessage);
//             } else {
//                 errorMap.put(fieldName, addMapValue(errorMessage, new ArrayList<String>()));
//             }
//         }
//         return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
//     }
// }


package com.omercngoktas.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Spring validation 'dan fırlatılan hataları alıp yönetmek ve adam akıllı response dönmek.
	
	private List<String> addMapValue(List<String> list , String newValue){
		list.add(newValue);
		return list;
	}
	
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, List<String>> errorsMap  =new HashMap<>();
		
		for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
		  String fieldName=	((FieldError)objError).getField();
		  if(errorsMap.containsKey(fieldName)) {
			  errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objError.getDefaultMessage()));
		  }else {
			  errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));
		  }
		  
		}
	return ResponseEntity.badRequest().body(createApiError(errorsMap));
		
	}
	
	private <T> ApiError<T> createApiError(T errors) {
		ApiError<T> apiError = new ApiError<T>();
		apiError.setId(UUID.randomUUID().toString());
		apiError.setErrorTime(new Date());
		apiError.setErrors(errors);
		
		return apiError;
	}
	
	
	
	
	
	
	
	
}
