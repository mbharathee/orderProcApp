package com.example.membership.service;



import com.example.membership.model.Membership;
import com.example.membership.repository.MembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public String activateMembership(String username) {
        Membership membership = membershipRepository.findByUsername(username)           
        		.orElse(new Membership(null, username, false));

        membership.setActive(true);
        membershipRepository.save(membership);

        return "Membership activated successfully for " + username;
    }
}

