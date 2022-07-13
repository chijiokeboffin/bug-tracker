package com.ratepay.bugtracker.bug.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;



public record BugCreateRequest(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "AssignTo is required") String assignTo,
        @NotBlank(message = "CreatedBy is required") String createdBy) {
}
