package br.com.strack.springboot_study.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
