package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TripChildPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213955184546696884L;

	private Long idTrip;
	private Long idChild;

	public TripChildPK() {
	}

	public Long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}

	public Long getIdChild() {
		return idChild;
	}

	public void setIdChild(Long idChild) {
		this.idChild = idChild;
	}
}
