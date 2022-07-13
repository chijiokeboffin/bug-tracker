package com.ratepay.bugtracker.bug.service;


import com.ratepay.bugtracker.bug.dto.ApiResponse;
import com.ratepay.bugtracker.bug.dto.BugCreateRequest;
import com.ratepay.bugtracker.bug.dto.BugUpdateRequest;
import com.ratepay.bugtracker.bug.exception.NoRecordFoundException;
import com.ratepay.bugtracker.bug.model.Bug;
import com.ratepay.bugtracker.bug.repository.BugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class BugService implements IBugService {

    private final BugRepository bugRepository;

    @Override
    public ResponseEntity<ApiResponse> createBug(BugCreateRequest createRequest) {
        Bug bug = Bug
                .builder()
                .title(createRequest.title())
                .createdBy(createRequest.createdBy())
                .assignTo(createRequest.assignTo())
                .createdDate(LocalDateTime.now())
                .isClosed(false)
                .lastModifiedBy(createRequest.createdBy())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        bugRepository.save(bug);

        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.OK.value()).message("Bug created successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<ApiResponse> list(int pageNo, int pageSize) {

        int enteredPageSize = pageSize == 0 ? 50 : pageSize;

        PageRequest pageRequest = PageRequest.of(pageNo, enteredPageSize);
        var list = bugRepository.findAll(pageRequest).stream().toList();
        if(list == null || list.size() <=0){
           throw new NoRecordFoundException("No record found in the database");
        }

        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(list)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<ApiResponse> updateBug(long bugId, BugUpdateRequest updateRequest) {
      var bug =  bugRepository.findById(bugId)
                .orElseThrow(()->
                        new NoRecordFoundException("No record found with ID %s".formatted(bugId)));

      if(Objects.nonNull(updateRequest.title())){
          bug.setTitle(updateRequest.title());
      }
      if(Objects.nonNull(updateRequest.assignTo())){
          bug.setAssignTo(updateRequest.assignTo());
      }
      if(Objects.nonNull(updateRequest.isClosed())){
          bug.setIsClosed(updateRequest.isClosed());
      }
      bug.setLastModifiedBy(updateRequest.lastModifiedBy());
      bug.setLastModifiedDate(LocalDateTime.now());

        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("updated successfully")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteBug(long bugId) {
        var bug =  bugRepository.findById(bugId)
                .orElseThrow(()->
                        new NoRecordFoundException("No record found with ID %s".formatted(bugId)));

        bugRepository.delete(bug);

        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("deleted successfully")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
