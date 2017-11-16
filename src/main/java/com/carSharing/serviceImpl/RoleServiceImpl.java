package com.carSharing.serviceImpl;

/**
 * Class ServiceImpl Role
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carSharing.model.Role;
import com.carSharing.repository.RoleRepository;
import com.carSharing.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    // Repository
    @Autowired
    final RoleRepository roleRepository;
    
    // Contructor
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    /**
     * find all roles
     */
    @Override
    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }
    
}