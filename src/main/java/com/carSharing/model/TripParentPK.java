package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TripParentPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7292470029718588385L;
	private Long idParent;
	private Long idTrip;

	public TripParentPK() {
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	public Long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}

}
