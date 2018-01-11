package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model tripchild
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "tripchild")
public class TripChild implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -757080301618885120L;
	@EmbeddedId
	private TripChildPK id;

	
    public TripChildPK getId() {
    
        return id;
    }

    
    public void setId(TripChildPK id) {
    
        this.id = id;
    }

    @ManyToOne
	private Trip trip;
	@ManyToOne
	private Child child;

	public TripChild() {
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
