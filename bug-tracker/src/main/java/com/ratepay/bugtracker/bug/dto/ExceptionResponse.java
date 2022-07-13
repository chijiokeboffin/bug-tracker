package com.ratepay.bugtracker.bug.dto;


import org.springframework.http.HttpStatus;

public record ExceptionResponse(HttpStatus status, String message) {
}
