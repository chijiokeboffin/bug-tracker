package com.ratepay.bugtracker.bug.dto;

import javax.validation.constraints.NotBlank;

public record BugUpdateRequest(
        String title,
        String assignTo,
        @NotBlank(message = "LastModifiedBy is required") String lastModifiedBy,
        Boolean isClosed) {
}
