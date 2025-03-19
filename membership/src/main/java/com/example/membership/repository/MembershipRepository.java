package com.example.membership.repository;



import com.example.membership.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUsername(String username);
}

