package com.carSharing.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model excursion
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "excursion")
public class Excursion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008028305456336686L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long days;
	@OneToMany(mappedBy = "excursion")
	private List<Trip> trips;
	@OneToMany(mappedBy = "excursion")
	private List<ExcursionGroup> excursionGroups;

	public Excursion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<ExcursionGroup> getExcursionGroups() {
		return excursionGroups;
	}

	public void setExcursionGroups(List<ExcursionGroup> excursionGroups) {
		this.excursionGroups = excursionGroups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
