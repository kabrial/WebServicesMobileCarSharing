package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ExcursionGroupPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 493159727464251898L;
	private Long idGroup;
	private Long idExcursion;

	public ExcursionGroupPK() {
	}

	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public Long getIdExcursion() {
		return idExcursion;
	}

	public void setIdExcursion(Long idExcursion) {
		this.idExcursion = idExcursion;
	}

}
