package com.ratepay.bugtracker.bug.service;

import com.ratepay.bugtracker.bug.dto.ApiResponse;
import com.ratepay.bugtracker.bug.dto.BugCreateRequest;
import com.ratepay.bugtracker.bug.dto.BugUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface IBugService {
    ResponseEntity<ApiResponse> createBug(BugCreateRequest createRequest);
    ResponseEntity<ApiResponse> list(int pageNo, int pageSize);
    ResponseEntity<ApiResponse> updateBug(long bugId, BugUpdateRequest updateRequest);
    ResponseEntity<ApiResponse> deleteBug(long bugId);
}
