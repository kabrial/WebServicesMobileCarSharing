package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model user role
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{
    
    
    private static final long serialVersionUID = -8522995571238845489L;
    @Id
    @Column
    private Long user_id;
    @Column
    private Long role_id;
    
    public Long getUser_id() {
    
        return user_id;
    }
    
    public void setUser_id(Long user_id) {
    
        this.user_id = user_id;
    }
    
    public Long getRole_id() {
    
        return role_id;
    }
    
    public void setRole_id(Long role_id) {
    
        this.role_id = role_id;
    }

}