package com.example.membership.controller;



import com.example.membership.service.MembershipService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/activate")
    public String activateMembership(@RequestParam String username) {
        return membershipService.activateMembership(username);
    }
}
