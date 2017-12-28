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
 * Model child
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "child")
public class Child implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7216297995877899084L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
	private User parent;
	@ManyToOne
	private Group group;
	@OneToMany(mappedBy = "child")
	private List<TripChild> tripChilds;

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

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
