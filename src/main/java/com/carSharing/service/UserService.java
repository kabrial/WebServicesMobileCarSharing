package com.carSharing.service;

import com.carSharing.model.User;
/**
 * Interface Service User
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface UserService {
    void save(User user);
    
    void saveModifyPassword(User user, long userId);
    
    User displayAUser(User user);

    User findByUsername(String username);
    
    Iterable<User> findAllUsers();
    
    User findUser(Long id);
    
    void saveUser(User user); 
    
    void updateUser(User user, long userId);

}
