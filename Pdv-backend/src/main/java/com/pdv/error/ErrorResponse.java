package com.pdv.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	private HttpStatus estado;
	@JsonFormat(pattern = "dd-MM-yy HH:mm")
	private LocalDateTime fechaYHora;
	private String mensaje;
}