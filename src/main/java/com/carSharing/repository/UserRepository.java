package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.User;
/**
 * Repository User
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
}
