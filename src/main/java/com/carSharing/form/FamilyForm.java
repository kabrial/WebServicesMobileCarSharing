package com.carSharing.form;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import com.carSharing.model.Child;
import com.carSharing.model.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class FamilyForm implements Serializable {

   

    /**
	 * 
	 */
	private static final long serialVersionUID = -8546116890435163971L;
	private int nbrPersonnes;
    private User parent;
    private List<Child> children;

    public List<Child> getChildren() {

        return children;
    }

    public void setChildren(List<Child> children) {

        this.children = children;
    }

    public User getParent() {

        return parent;
    }

    public void setParent(User parent) {

        this.parent = parent;
    }

    public int getNbrPersonnes() {

        return nbrPersonnes;
    }

    public void setNbrPersonnes(int nbrPersonnes) {

        this.nbrPersonnes = nbrPersonnes;
    }

}
