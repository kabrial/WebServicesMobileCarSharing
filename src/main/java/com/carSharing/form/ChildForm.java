package com.carSharing.form;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ChildForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5203615009706048196L;

    @Id
    private Long id;
    private String name;
    private String groupName;
    private Long idGroup;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getGroupName() {

        return groupName;
    }

    public void setGroupName(String groupName) {

        this.groupName = groupName;
    }

    public Long getIdGroup() {

        return idGroup;
    }

    public void setIdGroup(Long idGroup) {

        this.idGroup = idGroup;
    }

}
