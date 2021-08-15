package br.com.luaazul.logistica.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.luaazul.logistica.api.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ResponseErro responseErro = new ResponseErro();
		
		responseErro.setStatus(status.value());
		responseErro.setDataHora(LocalDateTime.now());
		responseErro.setTitulo("ERRO NA FORMATACAO DA REQUISICAO");
		
		Map<String, String> campos = new HashMap<String, String>();
		
		for(ObjectError erro: ex.getBindingResult().getAllErrors()) {
			campos.put( ((FieldError) erro).getField() , erro.getDefaultMessage());
		}
		
		responseErro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, responseErro, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		
		ResponseErro responseErro = new ResponseErro();
		
		responseErro.setStatus(status.value());
		responseErro.setDataHora(LocalDateTime.now());
		responseErro.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, responseErro, new HttpHeaders(), status, request);
	}
}
