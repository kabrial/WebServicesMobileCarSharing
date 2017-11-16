package com.carSharing.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.UserRole;

/**
 * Repository Role
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

  
}
