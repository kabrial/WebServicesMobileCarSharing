package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model tripparent
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "tripparent")
public class TripParent implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5743907611032910678L;
    @EmbeddedId
    private TripParentPK id;
    @ManyToOne
    private Trip trip;
    public TripParent() {
    }

    @ManyToOne
    private User parent;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
