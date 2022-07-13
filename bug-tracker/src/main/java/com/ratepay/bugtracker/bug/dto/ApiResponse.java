package com.ratepay.bugtracker.bug.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class ApiResponse {
    protected String message;
    protected int code;
    protected Object data;
}
