package com.carSharing.service;

import com.carSharing.model.Role;
/**
 * Interface Service Role
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
public interface RoleService {
    Iterable<Role> findAllRoles();
}
