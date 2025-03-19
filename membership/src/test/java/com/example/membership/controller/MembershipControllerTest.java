package com.example.membership.controller;



import com.example.membership.service.MembershipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembershipController.class)
class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MembershipService membershipService;

    @Test
    void testActivateMembership_Success() throws Exception {
        // Arrange
        String username = "john_doe";
        when(membershipService.activateMembership(username))
                .thenReturn("Membership activated successfully for john_doe");

        // Act & Assert
        mockMvc.perform(post("/api/membership/activate")
                .param("username", username))
                .andExpect(status().isOk())
                .andExpect(content().string("Membership activated successfully for john_doe"));
    }

    @Test
    void testActivateMembership_Failure() throws Exception {
        String username = "invalid_user";
        when(membershipService.activateMembership(username))
                .thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(post("/api/membership/activate")
                .param("username", username))
                .andExpect(status().isInternalServerError());
    }
}

