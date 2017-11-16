package com.carSharing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carSharing.repository.UserRepository;
import com.carSharing.repository.UserRoleRepository;
import com.carSharing.service.UserRoleDeleteService;

/**
 * UserRoleServiceImpl.
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Service
public class UserRoleDeleteServiceImpl implements UserRoleDeleteService {
    
    // Repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    /**
     * delete account
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }
    
    /**
     * delete user_role
     * @param user_id
     */
    @Override
    public void deleteUserRoleByUserid(Long user_id) {
        userRoleRepository.delete(user_id);
    }
}
