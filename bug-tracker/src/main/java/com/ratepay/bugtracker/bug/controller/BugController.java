package com.ratepay.bugtracker.bug.controller;

import com.ratepay.bugtracker.bug.dto.ApiResponse;
import com.ratepay.bugtracker.bug.dto.BugCreateRequest;
import com.ratepay.bugtracker.bug.dto.BugUpdateRequest;
import com.ratepay.bugtracker.bug.service.IBugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/bug")
public class BugController {

    private  final IBugService bugService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBug(@Valid @RequestBody BugCreateRequest request){
        return  bugService.createBug(request);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> list(@RequestParam int pageNo, @RequestParam int pageSize){
        return bugService.list(pageNo, pageSize);
    }

    @PutMapping("/update/{bugId}")
    public  ResponseEntity<ApiResponse> updateBug(@PathVariable("bugId") long bugId,
                                                  @Valid @RequestBody BugUpdateRequest request){
        return bugService.updateBug(bugId, request);
    }

    @DeleteMapping("/deleteBug/{bugId}")
    public  ResponseEntity<ApiResponse> deleteBug(@PathVariable("bugId") int bugId){
        return bugService.deleteBug(bugId);
    }
}
