package com.ratepay.bugtracker.bug.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratepay.bugtracker.bug.dto.ApiResponse;
import com.ratepay.bugtracker.bug.dto.BugCreateRequest;
import com.ratepay.bugtracker.bug.service.IBugService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BugController.class)
class BugControllerTest {

    @MockBean
    private IBugService bugService;

    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    void setUp() {

        ApiResponse response = ApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .message("created successfully")
                .build();
        var createResponse =  ResponseEntity.status(HttpStatus.OK).body(response);
        BugCreateRequest request = new
                BugCreateRequest("High Latency", "Nicolas", "Admin");
        Mockito.when(bugService.createBug(request)).thenReturn(createResponse);

    }

    @Test
    void createBug() throws Exception {


        String title = "Email Failing";
        String assignTo = "Zendev";
        String assignBy = "Admin";

        String body = "{" +
                "\"title\":\""  + title + "\"," +
                " \"assignTo\":\"" + assignTo +
                "\"assignBy\":" + assignBy +  "\"}";

        var mvcResult =  mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void list() {
    }

    @Test
    void updateBug() {
    }

    @Test
    void deleteBug() {
    }
}