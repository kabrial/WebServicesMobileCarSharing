package com.carSharing.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model trip
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "trip")
public class Trip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085607423125570636L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dateDepart;
    private long numberPlaces;
    private long placesReserved;
	
    public long getPlacesReserved() {
    
        return placesReserved;
    }

    
    public void setPlacesReserved(long placesReserved) {
    
        this.placesReserved = placesReserved;
    }

    @ManyToOne
	private User parent;
	@ManyToOne
	private Excursion excursion;
	@OneToMany(mappedBy = "trip")
	private List<TripChild> tripChilds;

	public Trip() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public long getNumberPlaces() {
		return numberPlaces;
	}

	public void setNumberPlaces(long numberPlaces) {
		this.numberPlaces = numberPlaces;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	public List<TripChild> getTripChilds() {
		return tripChilds;
	}

	public void setTripChilds(List<TripChild> tripChilds) {
		this.tripChilds = tripChilds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
