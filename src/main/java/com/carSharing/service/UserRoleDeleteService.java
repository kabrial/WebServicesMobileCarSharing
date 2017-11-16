package com.carSharing.service;

/**
 * Interface UserRole Service.
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface UserRoleDeleteService {
    
    void deleteUserById(Long id);
    
    void deleteUserRoleByUserid(Long user_id);
}
