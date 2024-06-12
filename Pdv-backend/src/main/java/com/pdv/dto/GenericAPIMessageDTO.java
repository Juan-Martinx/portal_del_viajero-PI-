package com.pdv.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericAPIMessageDTO {
	private HttpStatus estado;
	@JsonFormat(pattern = "dd-MM-yy HH:mm")
	private LocalDateTime fechaYHora;
	private String mensaje;
}