package com.carSharing.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Role;
/**
 * Repository Role
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    List<Role> findById(Long id);
}
