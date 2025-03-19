package com.example.membership.controller;

import com.example.membership.model.Membership;
import com.example.membership.repository.MembershipRepository;
import com.example.membership.service.MembershipService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MembershipServiceTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipService membershipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testActivateMembership_WhenUserExists() {
        // Arrange
        String username = "john_doe";
        Membership existingMembership = new Membership(1L, username, false);

        when(membershipRepository.findByUsername(username))
                .thenReturn(Optional.of(existingMembership));

        // Act
        String result = membershipService.activateMembership(username);

        // Assert
        assertEquals("Membership activated successfully for john_doe", result);
        assertTrue(existingMembership.isActive());
        verify(membershipRepository, times(1)).save(existingMembership);
    }

    @Test
    void testActivateMembership_WhenUserDoesNotExist() {
        // Arrange
        String username = "new_user";
        when(membershipRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        ArgumentCaptor<Membership> membershipCaptor = ArgumentCaptor.forClass(Membership.class);

        // Act
        String result = membershipService.activateMembership(username);

        // Assert
        assertEquals("Membership activated successfully for new_user", result);

        // Verify new membership creation
        verify(membershipRepository).save(membershipCaptor.capture());
        Membership savedMembership = membershipCaptor.getValue();

        assertNotNull(savedMembership);
        assertEquals("new_user", savedMembership.getUsername());
        assertTrue(savedMembership.isActive());
    }
}


