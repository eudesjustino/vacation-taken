package com.br.hrxpto.vacation.handle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.br.hrxpto.vacation.error.ErrorDetails;
import com.br.hrxpto.vacation.exception.ExistingCredentialsException;
import com.br.hrxpto.vacation.exception.InvalidCredentialsException;
import com.br.hrxpto.vacation.exception.VacationDataPeriodException;

/**
 * @author eudes.justino
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(VacationDataPeriodException.class)
	public ResponseEntity<?> hadleVacationDataPeriodException(VacationDataPeriodException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.title("Date Range Match")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ExistingCredentialsException.class)
	public ResponseEntity<?> hadleErrorExistingCredentialsException(ExistingCredentialsException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.title("Already Registered User")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> hadleInvalidCredentialsException(InvalidCredentialsException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.UNAUTHORIZED.value())
			.title("Invalid Credentials")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, headers, status);
	}

}
