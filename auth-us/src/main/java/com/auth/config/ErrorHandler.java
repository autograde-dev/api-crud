/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author nicoc
 */
@ControllerAdvice
public class ErrorHandler {
    private static Logger LOG = Logger.getLogger(ErrorHandler.class.getName());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception e) {

		LOG.severe(e.getMessage());

		// Construye el cuerpo del JSON de error
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("code", "500");
		errorBody.put("error", "Internal Server Error");
		errorBody.put("timestamp", getCurrentTimestamp());

		// Devuelve un mensaje genérico al cliente
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
	}

	private String getCurrentTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(new Date());
	}
	
}
